package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.OrderUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.TestPushApi;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.dto.OrderBaseDTO;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户订单 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
@Service
public class OrdersServiceImpl implements IOrdersService 
{
    @Resource
    private OrdersMapper ordersMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static int payTimeLimit;
    @Value("${order-pay.limit-time}")
    public void setPayTimeLimit(int payTimeLimit) {
        OrdersServiceImpl.payTimeLimit = payTimeLimit;
    }

    @Override
    public AjaxResult placeOrder(OrderBaseDTO orderBaseDTO) {
        //1、创建订单
        Orders order = new Orders();
        Long orderId = SnowflakeUtils.nextId();
        String orderNo = OrderUtils.generateOrderNo();
        order.setId(orderId);
        order.setOrderNo(orderNo);
        //2、完善订单基本信息
        orderPerfect(orderBaseDTO, order);
        //3、保存订单
        int i = ordersMapper.insertOrders(order);
        //4、加入延迟消息队列（通知进行后续业务...）
        this.sendToRedis(orderNo, SecurityUtils.getUserId().toString());
        //5、穿透消息发送
        String placeOrderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getPlacedTime());
        StringBuilder sb = new StringBuilder("下单通知：");
        sb.append("订单号")
                .append(orderNo)
                .append(" 服务名称：")
                .append(orderBaseDTO.getServiceCategoryName())
                .append("--")
                .append(orderBaseDTO.getServiceInfoId())
                .append(" 订单金额：")
                .append(orderBaseDTO.getOrderPrice())
                .append(" 下单时间:")
                .append(placeOrderTime)
                .append(".");
        TestPushApi.msgThrough("81f4d37dc4bb9dbfb09a9e2d0eb9f2c2", sb.toString());

        //响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("orderId", orderId.toString());
        return i > 0 ? AjaxResult.success(map):AjaxResult.error("下单失败");
    }

    /** 完善订单基本信息 */
    private void orderPerfect(OrderBaseDTO orderBaseDTO, Orders order){
        order.setCategoryId(orderBaseDTO.getServiceCategoryId());
        order.setServiceInfoId(orderBaseDTO.getServiceInfoId());
        order.setTitle(orderBaseDTO.getServiceCategoryName().concat("--").concat(orderBaseDTO.getServiceName()));
        order.setPrice(orderBaseDTO.getOrderPrice());
        order.setSnapData(JSON.toJSONString(orderBaseDTO));

        Calendar expiredTime = Calendar.getInstance();
        Calendar placedTime = (Calendar) expiredTime.clone();
        expiredTime.add(Calendar.MINUTE, OrdersServiceImpl.payTimeLimit);

        order.setPlacedTime(placedTime.getTime());
        order.setExpiredTime(expiredTime.getTime());
        order.setUserId(SecurityUtils.getUserId());
        order.setCreatedBy(SecurityUtils.getUserId());
        order.setCreatedTime(placedTime.getTime());
        order.setOrderStatus(OrderConstants.WAITING_PAY);//新订单-待支付
    }


    //技术原因导致下单失败不能抛出异常，资金损失是平台无法接受的
    private void sendToRedis(String orderNo, String userId){
        try {
            String key = orderNo + "#" + userId;
            stringRedisTemplate.opsForValue().set(key, "1", OrdersServiceImpl.payTimeLimit, TimeUnit.SECONDS);
        } catch (Exception e) {
            //todo 建议加入系统预警（强提醒：CMS、运营人员）
            e.printStackTrace();
        }
    }

    /**
     * 查询用户订单 
     * 
     * @param id 用户订单 主键
     * @return 用户订单 
     */
    @Override
    public Orders selectOrdersById(Long id)
    {
        return ordersMapper.selectOrdersById(id);
    }

    /**
     * 查询用户订单 列表
     * 
     * @param orders 用户订单 
     * @return 用户订单 
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders)
    {
        /*注意redis键失效调用不到，所以不要在上下文中获取*/
//        orders.setUserId(SecurityUtils.getUserId());
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 新增用户订单 
     * 
     * @param orders 用户订单 
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 用户取消订单
     * 
     * @param order 用户订单
     * @return 结果
     */
    @Override
    public AjaxResult cancelOrder(Orders order)
    {
        List<Orders> orders = ordersMapper.selectOrdersList(order);
        if (orders.size()==0) {
            return AjaxResult.error("订单不存在~");
        }
        Orders orderDB = orders.get(0);
        orderDB.setOrderStatus(OrderConstants.CANCELED);//订单状态：取消
        orderDB.setCancelType(OrderConstants.CANCEL_USER);//用户主动取消
        int i = ordersMapper.updateOrders(orderDB);
        return i>0 ? AjaxResult.success("取消成功~"):AjaxResult.error("取消失败~");
    }

    /**
     * 批量删除用户订单 
     * 
     * @param ids 需要删除的用户订单 主键
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(Long[] ids)
    {
        return ordersMapper.deleteOrdersByIds(ids);
    }

    /**
     * 删除用户订单 信息
     * 
     * @param id 用户订单 主键
     * @return 结果
     */
    @Override
    public int deleteOrdersById(Long id)
    {
        return ordersMapper.deleteOrdersById(id);
    }
}
