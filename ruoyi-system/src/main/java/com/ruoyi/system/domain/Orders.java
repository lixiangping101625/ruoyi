package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户订单 对象 orders
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
@Data
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单编码 */
    @Excel(name = "订单编码")
    private String orderNo;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
    /** 服务分类id */
    @Excel(name = "服务分类id")
    private Long categoryId;
    /** 服务id */
    @Excel(name = "服务id")
    private Long serviceInfoId;

    /** 订单名称 */
    @Excel(name = "订单名称")
    private String title;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal price;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private BigDecimal finalPrice;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date placedTime;

    /** 订单过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date delTime;

    /** 详情快照（json） */
    @Excel(name = "详情快照", readConverterExp = "j=son")
    private String snapData;

    /** 订单状态：1-待支付2-待接单（已付款）3-待服务（已接单）4-服务中5-完成6-已取消7-退款 */
    @Excel(name = "订单状态：1-待支付2-待接单", readConverterExp = "已=付款")
    private Integer orderStatus;

    /** 订单取消类型:1-超时未支付2-用户主动取消 */
    @Excel(name = "订单取消类型:1-超时未支付2-用户主动取消")
    private Integer cancelType;

    /** 退款状态：1-退款中2-拒绝3-退款成功4-退款失败 */
    @Excel(name = "退款状态：1-退款中2-拒绝3-退款成功4-退款失败")
    private Integer refundStatus;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private Long updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
