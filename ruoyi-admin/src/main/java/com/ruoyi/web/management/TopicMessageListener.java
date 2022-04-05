package com.ruoyi.web.management;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.utils.TestPushApi;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.ServiceCategory;
import com.ruoyi.system.domain.ServiceInfo;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.mapper.ServiceCategoryMapper;
import com.ruoyi.system.mapper.ServiceInfoMapper;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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

    @Resource
    private IOrdersService orderService;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private ServiceCategoryMapper categoryMapper;
    @Resource
    private ServiceInfoMapper serviceInfoMapper;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

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
        String orderNo = split[1];
        String userId = split[2];

        if (split[0].equals("order_place")) {//订单相关
            Orders queryDomain = new Orders();
            queryDomain.setUserId(Long.parseLong(userId));
            queryDomain.setOrderNo(orderNo);

            List<Orders> orders = orderService.selectOrdersList(queryDomain);
            if (orders.size()>0) {
                Orders order = orders.get(0);
                order.setOrderStatus(OrderConstants.CANCELED);
                order.setCancelType(OrderConstants.CANCEL_OUT_TIME);
                int i = ordersMapper.updateOrders(order);
                if (i>0){
                    //5、穿透消息发送
                    threadPoolTaskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String placeOrderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getPlacedTime());
                            ServiceCategory serviceCategory = categoryMapper.selectServiceCategoryById(order.getCategoryId());
                            ServiceInfo serviceInfo = serviceInfoMapper.selectServiceInfoById(order.getServiceInfoId());
                            StringBuilder sb = new StringBuilder("订单取消通知：");
                            sb.append("您的订单：")
                                    .append(orderNo)
                                    .append(" 服务名称：")
                                    .append(serviceCategory.getName())
                                    .append("--")
                                    .append(serviceInfo.getServiceName())
                                    .append(" 订单金额：")
                                    .append(order.getPrice())
                                    .append("超时未支付，已自动取消。")
                                    .append(" 下单时间:")
                                    .append(placeOrderTime)
                                    .append(".");
                            TestPushApi.msgThrough("81f4d37dc4bb9dbfb09a9e2d0eb9f2c2", sb.toString());
                        }
                    });
                }
            }
        }
    }
}