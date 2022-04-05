package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 城市设置对象 administration_city
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
public class AdministrationCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long cityId;

    /** 市代码 */
    @Excel(name = "市代码")
    private String cityCode;

    /** 市名称 */
    @Excel(name = "市名称")
    private String cityName;

    /** 简称 */
    @Excel(name = "简称")
    private String shortName;

    /** 省代码 */
    @Excel(name = "省代码")
    private String provinceCode;

    /** 经度 */
    @Excel(name = "经度")
    private String lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lat;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

    /** 状态 */
    @Excel(name = "状态")
    private Integer dataState;

    /** 租户ID */
    @Excel(name = "租户ID")
    private String tenantCode;

    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setCityCode(String cityCode) 
    {
        this.cityCode = cityCode;
    }

    public String getCityCode() 
    {
        return cityCode;
    }
    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() 
    {
        return cityName;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setProvinceCode(String provinceCode) 
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() 
    {
        return provinceCode;
    }
    public void setLng(String lng) 
    {
        this.lng = lng;
    }

    public String getLng() 
    {
        return lng;
    }
    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() 
    {
        return gmtModified;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setDataState(Integer dataState)
    {
        this.dataState = dataState;
    }

    public Integer getDataState()
    {
        return dataState;
    }
    public void setTenantCode(String tenantCode) 
    {
        this.tenantCode = tenantCode;
    }

    public String getTenantCode() 
    {
        return tenantCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cityId", getCityId())
            .append("cityCode", getCityCode())
            .append("cityName", getCityName())
            .append("shortName", getShortName())
            .append("provinceCode", getProvinceCode())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("sort", getSort())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("dataState", getDataState())
            .append("tenantCode", getTenantCode())
            .toString();
    }
}
