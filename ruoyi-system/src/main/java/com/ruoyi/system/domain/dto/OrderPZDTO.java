package com.ruoyi.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年03月16日 18:18
 * @decription: 陪诊服务下单DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPZDTO implements Serializable {

    private String serviceCategoryName;

    private Long serviceInfoId;
    private String serviceName;
    private Long patientId;
    private String patientName;
    private Integer age;
    private String gender;
    private String contact;

    private Long hospitalId;
    private String hospitalName;
    private Long officeId;
    private String officeName;

    private Date expectServiceTime;
    private Integer isAppoint;//是否预约挂号
    private Long doctorId;//
    private String doctorName;

    private String relationShip;
    private String appointContact;//预约人联系方式
    private String specialRequirement;//特殊需求

    private BigDecimal orderPrice;//订单价格

}
