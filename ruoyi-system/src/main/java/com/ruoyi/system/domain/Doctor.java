package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约医生 对象 doctor
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Data
public class Doctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 性别：0-女 1-男 */
    @Excel(name = "性别：0-女 1-男")
    private Long gender;

    /** 医院id */
    @Excel(name = "医院id")
    private Long hospitalId;

    /** 科室id */
    @Excel(name = "科室id")
    private Long departofficeId;

    /** 医生介绍 */
    @Excel(name = "医生介绍")
    private String introduce;

    /** 状态：0-正常1-禁用 */
    @Excel(name = "状态：0-正常1-禁用")
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

}
