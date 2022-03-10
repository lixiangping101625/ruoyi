package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.AdministrationCity;
import com.ruoyi.system.domain.AdministrationProvince;
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

    private AdministrationProvince province;

    private List<AdministrationCity> cities;
}
