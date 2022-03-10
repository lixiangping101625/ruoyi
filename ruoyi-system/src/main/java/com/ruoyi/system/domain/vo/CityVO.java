package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市设置对象 administration_city
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@Data
public class CityVO implements Serializable
{

    private Long cityId;
    private String cityCode;
    private String cityName;
    private String provinceCode;

}
