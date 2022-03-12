package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * 科室信息 对象 hospital_office
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Data
public class HospitalOffice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String name;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hospitalName;

    /** 医院id */
    @Excel(name = "医院id")
    private Long hospitalId;

    /** 上级id */
    @Excel(name = "上级id")
    private Long parentId;

    /** 状态：0-正常 1-禁用 */
    @Excel(name = "状态：0-正常 1-禁用")
    private Integer state;

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

    @Transient
    private List<HospitalOffice> subHospitalOffices;

}
