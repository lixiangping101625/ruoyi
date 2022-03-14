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
 * 用户储值金 对象 user_storegold
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Data
public class UserStoregold extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 当前储值金 */
    @Excel(name = "当前储值金")
    private BigDecimal currAmount;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private BigDecimal exchangeAmount;

    /** 交易类型：1-消费2-充值 */
    @Excel(name = "交易类型：1-消费2-充值")
    private Integer exchangeType;

    /** 订单id：消费 */
    @Excel(name = "订单id：消费")
    private Long orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
