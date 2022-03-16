package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.OrderUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.dto.OrderPZDTO;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
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
    public AjaxResult placeOrderPZ(OrderPZDTO orderPZDTO) {
        //1、计算订单演示支付时间
        //订单延迟支付过期时间
        Calendar expiredTime = Calendar.getInstance();
        Calendar placedTime = (Calendar) expiredTime.clone();
        expiredTime.add(Calendar.MINUTE, OrdersServiceImpl.payTimeLimit);
        //2、新建订单对象
        Orders order = new Orders();
        Long orderId = SnowflakeUtils.nextId();
        String orderNo = OrderUtils.generateOrderNo();
        order.setId(orderId);
        order.setOrderNo(orderNo);
        order.setTitle(orderPZDTO.getServiceCategoryName().concat("--").concat(orderPZDTO.getServiceName()));
        order.setPrice(orderPZDTO.getOrderPrice());
        order.setOrderStatus(OrderConstants.WAITING_PAY);
        order.setSnapData(JSON.toJSONString(orderPZDTO));
        order.setPlacedTime(placedTime.getTime());
        order.setExpiredTime(expiredTime.getTime());
        order.setUserId(SecurityUtils.getUserId());
        order.setCreatedBy(SecurityUtils.getUserId());
        order.setCreatedTime(placedTime.getTime());
        //3、保存订单
        int i = ordersMapper.insertOrders(order);
        //4、加入延迟消息队列（通知进行后续业务...）
        this.sendToRedis(orderNo, SecurityUtils.getUserId().toString());
        return i > 0 ? AjaxResult.success(orderNo):AjaxResult.error("下单失败");
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
     * 修改用户订单 
     * 
     * @param orders 用户订单 
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        return ordersMapper.updateOrders(orders);
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
