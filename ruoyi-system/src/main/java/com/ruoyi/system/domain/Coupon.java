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
 * 优惠券 对象 coupon
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Data
public class Coupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String name;

    /** 优惠券类型：1-满减券（默认）2-折扣券 */
    @Excel(name = "优惠券类型：1-满减券", readConverterExp = "默=认")
    private Long couponType;

    /** 最低消费-满减券) */
    @Excel(name = "最低消费-满减券)")
    private BigDecimal fullMoney;

    /** 减多少-满减券 */
    @Excel(name = "减多少-满减券")
    private BigDecimal minus;

    /** 折扣（折扣券使用）：1-9 */
    @Excel(name = "折扣", readConverterExp = "折=扣券使用")
    private BigDecimal rate;

    /** 是否全场通用：0-否1-是 */
    @Excel(name = "是否全场通用：0-否1-是")
    private Integer wholeStore;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
