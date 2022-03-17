package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年03月17日 15:32
 * @decription: 陪诊下单时dto
 */
@Data
public class PZOrderDTO extends OrderBaseDTO {


    private Long officeId;
    private String officeName;

    private Date expectServiceTime;
    private Integer isAppoint;//是否预约挂号
    private Long doctorId;//
    private String doctorName;

    private String relationShip;

}
