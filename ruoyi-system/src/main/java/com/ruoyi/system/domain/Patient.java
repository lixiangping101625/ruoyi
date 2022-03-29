package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 就诊人员 对象 patient
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@Data
public class Patient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别：0-女1-男2-未知 */
    @Excel(name = "性别：0-女1-男2-未知")
    private Integer gender;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 就诊人身份证号码 */
    @Excel(name = "就诊人身份证号码")
    private String cardNum;

    /** 客户端用户id */
    @Excel(name = "客户端用户id")
    private Long userId;

    /** 就诊人和用户关系 */
    @Excel(name = "就诊人和用户关系")
    private Integer relation;

    @Transient
    private String relationStr;

    /** 状态：0-正常1-删除 */
    @Excel(name = "状态：0-正常1-删除")
    private Integer state;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
}
