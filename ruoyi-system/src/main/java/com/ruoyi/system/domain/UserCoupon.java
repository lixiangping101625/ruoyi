package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户优惠券 对象 user_coupon
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Data
public class UserCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 优惠券id */
    @Excel(name = "优惠券id")
    private Long couponId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 使用状态：1-未使用2-已使用3-已过期 */
    @Excel(name = "使用状态：1-未使用2-已使用3-已过期")
    private Integer usingStatus;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
