package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户银行卡 对象 user_bankcard
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Data
public class UserBankcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 银行类型：1-农业2-招商3-建设4-工行5-交通 */
    @Excel(name = "银行类型：1-农业2-招商3-建设4-工行5-交通")
    private Long bankcardType;

    /** 银行卡姓名 */
    @Excel(name = "银行卡姓名")
    private String bankcardUsername;

    /** 银行卡身份证号 */
    @Excel(name = "银行卡身份证号")
    private String bankcardCard;

    /** 开户行地址 */
    @Excel(name = "开户行地址")
    private String bankcardOpenAddr;

    /** 银行卡卡号 */
    @Excel(name = "银行卡卡号")
    private String bankcardNo;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
