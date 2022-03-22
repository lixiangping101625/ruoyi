package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图;轮播图对象 swiper
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@Data
public class Swiper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createdBy;

    /** 主键 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 类型：保留字段 */
    @Excel(name = "类型：保留字段")
    private Long swiperType;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer order;

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