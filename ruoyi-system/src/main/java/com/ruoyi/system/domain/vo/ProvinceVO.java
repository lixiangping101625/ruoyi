package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 20:32
 * @decription:
 */
@Data
public class ProvinceVO implements Serializable {

    private Long provinceId;
    private String provinceCode;
    private String provinceName;

}
