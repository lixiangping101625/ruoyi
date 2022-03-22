package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医院信息 对象 hospital
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Data
public class Hospital extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String name;

    /** 省份id */
    @Excel(name = "省份id")
    private Long provId;

    /** 城市id */
    @Excel(name = "城市id")
    private Long cityId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddr;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 状态：0-禁用 1-正常 */
    @Excel(name = "状态：0-禁用 1-正常")
    private Integer businessStatus;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
