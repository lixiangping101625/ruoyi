package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年03月29日 16:13
 * @decription: 订单DTO
 */
@Data
public class OrderDTO implements Serializable {

    private Long serviceCategoryId;
    private Long serviceInfoId;
    private String serviceName;

    private Long patientId;
    private Long userId;

    private Long hospitalId;
    private String hospitalName;
    private Long officeId;
    private String officeName;

    private Integer serviceDayCode;//服务天编码：1-今天 2-明天
    private Date serviceTime;//服务时间

    private Integer serviceCycleDayCode;//服务周期天编码：1-今天 2-明天
    private Integer serviceCycleHourRangeCode;//1-上午（8-12点） 2-（下午13.30-17.00） 3-全天（8-17）

    private String remark;

//    private Long couponId;
    private Long userCouponId;
    private Integer payMethod;//支付方式

    private BigDecimal price;
    private BigDecimal finalPrice;

}
