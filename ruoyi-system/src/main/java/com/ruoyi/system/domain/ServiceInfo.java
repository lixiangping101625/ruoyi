package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务(陪诊)基本信息 对象 service_info
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public class ServiceInfo extends BaseEntity
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

    /** 服务类目id */
    @Excel(name = "服务类目id")
    private Long categoryId;

    /** 基本价格 */
    @Excel(name = "基本价格")
    private BigDecimal basePrice;

    /** 预约价格：服务类目‘陪诊’ */
    @Excel(name = "预约医生价格：服务类目‘陪诊’")
    private BigDecimal appointPrice;

    /** 状态：0-正常1-禁用 */
    @Excel(name = "状态：0-正常1-禁用")
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

}
