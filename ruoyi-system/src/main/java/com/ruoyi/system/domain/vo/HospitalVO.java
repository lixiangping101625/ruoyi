package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 医院信息 对象 hospital
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Data
public class HospitalVO implements Serializable
{

    /** 主键 */
    private Long id;
    /** 医院名称 */
    private String name;
    /** 省份id */
    private Long provId;
    /** 城市id */
    private Long cityId;
    /** 详细地址 */
    private String detailAddr;
    /** 经度 */
    private BigDecimal longitude;
    /** 纬度 */
    private BigDecimal latitude;
    /** 状态：1-禁用 0-正常 */
    private Integer businessStatus;

}
