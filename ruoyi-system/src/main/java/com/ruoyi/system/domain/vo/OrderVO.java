package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.dto.OrderBaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户订单 对象 orders
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
@Data
public class OrderVO implements Serializable
{
    /** 主键 */
    private Long id;
    /** 订单编码 */
    private String orderNo;
    /** 用户id */
    private Long userId;
    /** 订单名称 */
    private String title;
    /** 订单金额 */
    private BigDecimal price;
    /** 实付金额 */
    private BigDecimal finalPrice;
    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date placedTime;
    /** 订单过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    /** 详情 */
    private OrderBaseDTO orderBaseDTO;

    /** 订单状态：1-待支付2-待接单（已付款）3-待服务（已接单）4-服务中5-完成6-已取消7-退款 */
    private Integer orderStatus;
    /** 订单取消类型:1-超时未支付2-用户主动取消 */
    private Integer cancelType;
    /** 退款状态：1-退款中2-拒绝3-退款成功4-退款失败 */
    private Integer refundStatus;

    /** 创建人 */
    private Long createdBy;
    /** 创建时间 */
    private Date createdTime;

}
