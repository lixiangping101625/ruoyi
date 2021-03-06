package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 19:29
 * @decription: 业务开通城市VO
 */
@Data
public class ProvCityVO implements Serializable {

    private Long provinceId;
    private String provinceCode;
    private String provinceName;

    private List<CityVO> cities;
}
