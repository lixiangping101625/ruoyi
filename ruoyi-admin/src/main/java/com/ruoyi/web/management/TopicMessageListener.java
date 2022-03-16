package com.ruoyi.web.management;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.List;

/**
 * @program: mall-classic
 * @description: 监听redis通知，处理业务逻辑
 * @author: 李向平
 * @create: 2021-04-02 21:40
 */
public class TopicMessageListener implements MessageListener {

    // 指向自己实例的私有静态引用，主动创建
    private static TopicMessageListener singleton1 = new TopicMessageListener();

    // 私有的构造方法
    private TopicMessageListener(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static TopicMessageListener getSingleton1(){
        return singleton1;
    }

    @Autowired
    private IOrdersService orderService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String expiredKey = new String(body);
        String topic = new String(channel);
        System.out.println(expiredKey);
        System.out.println(topic);
        //业务逻辑(修改订单状态为已取消，修改取消类型为‘超时未支付’)
        String[] split = expiredKey.split("#");
        String orderNo = split[0];
        String userId = split[1];

        Orders queryDomain = new Orders();
        queryDomain.setUserId(Long.parseLong(userId));
        queryDomain.setOrderNo(orderNo);

        List<Orders> orders = orderService.selectOrdersList(queryDomain);
        if (orders.size()>0) {
            Orders order = orders.get(0);
            order.setOrderStatus(OrderConstants.CANCELED);
            order.setCancelType(OrderConstants.CANCEL_OUT_TIME);
            orderService.cancelOrder(order);
        }
    }
}