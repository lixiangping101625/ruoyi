package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.CouponTypeContants;
import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.OrderUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.TestPushApi;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.ServiceInfo;
import com.ruoyi.system.domain.UserCoupon;
import com.ruoyi.system.domain.dto.OrderBaseDTO;
import com.ruoyi.system.domain.dto.OrderDTO;
import com.ruoyi.system.mapper.CouponMapper;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.mapper.ServiceInfoMapper;
import com.ruoyi.system.mapper.UserCouponMapper;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private UserCouponMapper userCouponMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private ServiceInfoMapper infoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static int payTimeLimit;
    @Value("${order-pay.limit-time}")
    public void setPayTimeLimit(int payTimeLimit) {
        OrdersServiceImpl.payTimeLimit = payTimeLimit;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult placeOrder(OrderDTO orderDTO) {
        //1-2-3  金额&优惠券 校验
        AjaxResult ajaxResult = validateInfo(orderDTO);
        if (ajaxResult != null)//校验未通过
            return ajaxResult;

        //4、创建订单
        Orders order = generateOrder(orderDTO);
        //5、保存订单
        int i = ordersMapper.insertOrders(order);
        //6、加入延迟消息队列（通知进行后续业务...）
        String orderNo = order.getOrderNo();
        this.sendToRedis(orderNo, SecurityUtils.getUserId().toString());
        //7、穿透消息发送TopicMessageListener
        String placeOrderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getPlacedTime());
        StringBuilder sb = new StringBuilder("下单通知：");
        sb.append("订单号")
                .append(orderNo)
                .append(" 服务名称：")
                .append(orderDTO.getServiceName())
                .append("--")
                .append(orderDTO.getServiceInfoId())
                .append(" 订单金额：")
                .append(order.getPrice())
                .append(" 下单时间:")
                .append(placeOrderTime)
                .append(".");
        TestPushApi.msgThrough("81f4d37dc4bb9dbfb09a9e2d0eb9f2c2", sb.toString());

        //8、修改用户优惠券
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(orderDTO.getUserCouponId());
        userCoupon.setOrderId(order.getId());
        userCoupon.setUpdatedBy(SecurityUtils.getUserId().toString());
        userCoupon.setUpdatedTime(DateUtils.getNowDate());
        int i1 = userCouponMapper.updateUserCoupon(userCoupon);

        return AjaxResult.success("下单成功您的订单号：" + orderNo);
    }

    private AjaxResult validateInfo(OrderDTO orderDTO){
        BigDecimal servicePrice = BigDecimal.ZERO;
        Coupon coupon = null;
        //1、优惠券核实
        if (orderDTO.getUserCouponId()!=null){
            UserCoupon userCoupon = userCouponMapper.selectUserCouponById(orderDTO.getUserCouponId());
            if (userCoupon == null) {
                return AjaxResult.error("暂未查询到该优惠券~");
            }
            coupon = couponMapper.selectCouponById(userCoupon.getCouponId());
            if (coupon!=null && coupon.getEndTime().before(DateUtils.getNowDate())){
                return AjaxResult.error("优惠券已过期，请重新下单~");
            }
        }
        //2、服务核实
        Long infoId = orderDTO.getServiceInfoId();
        ServiceInfo serviceInfo = infoMapper.selectServiceInfoById(infoId);
        if (serviceInfo == null) {
            return AjaxResult.error("该项服务不存在~");
        }
        servicePrice = serviceInfo.getBasePrice();
        //3、金额核实
        if (coupon.getCouponType()== CouponTypeContants.FULL_MINUS_TICKET){//全场券
            BigDecimal realPrice = servicePrice.subtract(coupon.getMinus());
            if (realPrice.compareTo(orderDTO.getPrice()) != 0) {
                return AjaxResult.error("金额可能被篡改，下单失败~");
            }
        }
        return null;
    }

    private Orders generateOrder(OrderDTO orderDTO){
        Orders order = new Orders();
        Long orderId = SnowflakeUtils.nextId();
        String orderNo = OrderUtils.generateOrderNo();
        order.setId(orderId);
        order.setOrderNo(orderNo);
        order.setCategoryId(orderDTO.getServiceCategoryId());
        order.setServiceInfoId(orderDTO.getServiceInfoId());
        order.setTitle(orderDTO.getServiceName());
        order.setPrice(orderDTO.getPrice());
        order.setFinalPrice(orderDTO.getPrice());
        order.setSnapData(JSON.toJSONString(orderDTO));

        Calendar expiredTime = Calendar.getInstance();
        Calendar placedTime = (Calendar) expiredTime.clone();
        expiredTime.add(Calendar.MINUTE, OrdersServiceImpl.payTimeLimit);

        order.setPlacedTime(placedTime.getTime());
        order.setExpiredTime(expiredTime.getTime());
        order.setUserId(SecurityUtils.getUserId());
        order.setCreatedBy(SecurityUtils.getUserId());
        order.setCreatedTime(placedTime.getTime());
        order.setOrderStatus(OrderConstants.WAITING_PAY);//新订单-待支付

        return order;
    }

//    @Override
//    public AjaxResult placeOrder(OrderBaseDTO orderBaseDTO) {
//        //1、创建订单
//        Orders order = new Orders();
//        Long orderId = SnowflakeUtils.nextId();
//        String orderNo = OrderUtils.generateOrderNo();
//        order.setId(orderId);
//        order.setOrderNo(orderNo);
//        //2、完善订单基本信息
//        orderPerfect(orderBaseDTO, order);
//        //3、保存订单
//        int i = ordersMapper.insertOrders(order);
//        //4、加入延迟消息队列（通知进行后续业务...）
//        this.sendToRedis(orderNo, SecurityUtils.getUserId().toString());
//        //5、穿透消息发送
//        String placeOrderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getPlacedTime());
//        StringBuilder sb = new StringBuilder("下单通知：");
//        sb.append("订单号")
//                .append(orderNo)
//                .append(" 服务名称：")
//                .append(orderBaseDTO.getServiceCategoryName())
//                .append("--")
//                .append(orderBaseDTO.getServiceInfoId())
//                .append(" 订单金额：")
//                .append(orderBaseDTO.getOrderPrice())
//                .append(" 下单时间:")
//                .append(placeOrderTime)
//                .append(".");
//        TestPushApi.msgThrough("81f4d37dc4bb9dbfb09a9e2d0eb9f2c2", sb.toString());
//
//        //响应给前端
//        HashMap<String, String> map = new HashMap<>();
//        map.put("orderNo", orderNo);
//        map.put("orderId", orderId.toString());
//        return i > 0 ? AjaxResult.success(map):AjaxResult.error("下单失败");
//    }
//
//    /** 完善订单基本信息 */
//    private void orderPerfect(OrderBaseDTO orderBaseDTO, Orders order){
//        order.setCategoryId(orderBaseDTO.getServiceCategoryId());
//        order.setServiceInfoId(orderBaseDTO.getServiceInfoId());
//        order.setTitle(orderBaseDTO.getServiceCategoryName().concat("--").concat(orderBaseDTO.getServiceName()));
//        order.setPrice(orderBaseDTO.getOrderPrice());
//        order.setSnapData(JSON.toJSONString(orderBaseDTO));
//
//        Calendar expiredTime = Calendar.getInstance();
//        Calendar placedTime = (Calendar) expiredTime.clone();
//        expiredTime.add(Calendar.MINUTE, OrdersServiceImpl.payTimeLimit);
//
//        order.setPlacedTime(placedTime.getTime());
//        order.setExpiredTime(expiredTime.getTime());
//        order.setUserId(SecurityUtils.getUserId());
//        order.setCreatedBy(SecurityUtils.getUserId());
//        order.setCreatedTime(placedTime.getTime());
//        order.setOrderStatus(OrderConstants.WAITING_PAY);//新订单-待支付
//    }


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
