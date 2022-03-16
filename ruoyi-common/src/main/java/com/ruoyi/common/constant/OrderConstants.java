package com.ruoyi.common.constant;

/**
 * @author Lixiangping
 * @createTime 2022年03月16日 18:14
 * @decription: 订单常量类
 */
public class OrderConstants {
    /*1-待支付2-待接单（已付款）3-待服务（已接单）4-服务中5-完成6-已取消7-退款*/
    public final static Integer WAITING_PAY = 1;
    public final static Integer ALREADY_PAY = 2;
    public final static Integer WAITING_SERVICE = 3;
    public final static Integer SERVICING = 4;
    public final static Integer COMPLETED = 5;
    public final static Integer CANCELED = 6;
    public final static Integer REFUND = 7;

    public final static Integer CANCEL_USER = 1;/*用户主动取消订单*/
    public final static Integer CANCEL_OUT_TIME = 2;/*超时未支付*/

}
