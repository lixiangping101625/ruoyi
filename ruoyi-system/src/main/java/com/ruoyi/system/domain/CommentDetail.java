package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户点评媒体 对象 comment_detail
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@Data
public class CommentDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户点评id */
    @Excel(name = "用户点评id")
    private Long commentId;

    /** 媒体类型：1-图片2-视频 */
    @Excel(name = "媒体类型：1-图片2-视频")
    private Integer mediaType;

    /** 媒体url */
    @Excel(name = "媒体url")
    private String url;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private Long updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
}
