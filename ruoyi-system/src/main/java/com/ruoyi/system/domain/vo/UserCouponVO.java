package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年03月14日 15:41
 * @decription: 用户优惠券VO
 */
@Data
public class UserCouponVO implements Serializable {

    /** 主键 */
    private Long id;
    /** 优惠券名称 */
    private String name;
    /** 优惠券类型：1-满减券（默认）2-折扣券 */
    private Long couponType;
    /** 最低消费-满减券) */
    private BigDecimal fullMoney;
    /** 减多少-满减券 */
    private BigDecimal minus;
    /** 折扣（折扣券使用）：1-9 */
    private BigDecimal rate;
    /** 是否全场通用：0-否1-是 */
    private Integer wholeStore;

    /**  领取时间 */
    private Date receiveTime;
}
