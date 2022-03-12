package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约医生 对象 doctor
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Data
public class DoctorVO implements Serializable
{
    /** 主键 */
    private Long id;

    /** 姓名 */
    private String name;
    /** 年龄 */
    private Long age;
    /** 性别：0-女 1-男 */
    private Integer gender;
    private String genderStr;
    /** 医院id */
    private Long hospitalId;
    /** 科室id */
    private Long departofficeId;
    /** 医生介绍 */
    private String introduce;

}
