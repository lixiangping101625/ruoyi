package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 科室信息 对象 hospital_office
 * 
 * @author LiXiangPing
 * @date 2022-03-13
 */
@Data
public class HospitalOfficeVO implements Serializable
{

    /** 主键 */
    private String id;
    /** 科室名称 */
    private String name;
    /** 医院名称 */
    private String hospitalName;
    /** 医院id */
    private Long hospitalId;
    /** 上级id */
    private Long parentId;
    /** 状态：0-正常 1-禁用 */
    private Integer state;
    /** 子项 */
    private List<HospitalOfficeVO> subHospitalOffices;

}
