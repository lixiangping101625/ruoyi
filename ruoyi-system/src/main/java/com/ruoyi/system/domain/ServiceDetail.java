package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * （商品）服务定价;对象 service_goods
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
public class ServiceDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 服务类目编码 */
    @Excel(name = "服务类目编码")
    private Integer categoryCode;

    /** 基本价格 */
    @Excel(name = "基本价格")
    private BigDecimal basePrice;

    /** 是否由可选服务:0-无1-有 */
    @Excel(name = "是否由可选服务:0-无1-有")
    private String hasOtherSubservice;

    /** 状态：0-禁用1-正常 */
    @Excel(name = "状态：0-禁用1-正常")
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setBasePrice(BigDecimal basePrice)
    {
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice() 
    {
        return basePrice;
    }
    public void setHasOtherSubservice(String hasOtherSubservice) 
    {
        this.hasOtherSubservice = hasOtherSubservice;
    }

    public String getHasOtherSubservice() 
    {
        return hasOtherSubservice;
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
            .append("serviceName", getServiceName())
            .append("icon", getIcon())
            .append("serviceCategoryCode", getCategoryCode())
            .append("basePrice", getBasePrice())
            .append("hasOtherSubservice", getHasOtherSubservice())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
