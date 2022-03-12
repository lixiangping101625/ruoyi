package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * （商品）服务类目;服务类目对象 service_category
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@Data
public class ServiceCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类目编码:1-陪诊2-增值 */
    @Excel(name = "类目编码:1-陪诊2-增值")
    private Integer categoryCode;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String name;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 状态:0-禁用1-启用 */
    @Excel(name = "状态:0-禁用1-启用")
    private Long status;

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

    private List<ServiceDetail> services;

}
