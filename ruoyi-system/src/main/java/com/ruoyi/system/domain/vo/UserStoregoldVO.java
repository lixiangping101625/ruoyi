package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户储值金 对象VO
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Data
public class UserStoregoldVO implements Serializable
{

    /** 主键 */
    private Long id;
    /** 当前储值金 */
    private BigDecimal currAmount;
    /** 交易金额 */
    private BigDecimal exchangeAmount;
    /** 交易类型：1-消费2-充值 */
    private Integer exchangeType;
    private String exchangeTypeStr;
    /** 订单id：消费 */
    private Long orderId;
    /** 用户id */
    private Long userId;
    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdTime;

}
