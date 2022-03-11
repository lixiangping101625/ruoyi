package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * （商品）服务类目;服务类目对象 service_category
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
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

    /** 菜单级别：1-一级2-二级 */
    @Excel(name = "菜单级别：1-一级2-二级")
    private Integer menuLevel;

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

    @Transient
    private List<ServiceGoods> services;

    public List<ServiceGoods> getServices() {
        return services;
    }

    public void setServices(List<ServiceGoods> services) {
        this.services = services;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryCode(Integer categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public Integer getCategoryCode()
    {
        return categoryCode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMenuLevel(Integer menuLevel)
    {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuLevel()
    {
        return menuLevel;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryCode", getCategoryCode())
            .append("name", getName())
            .append("menuLevel", getMenuLevel())
            .append("icon", getIcon())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
