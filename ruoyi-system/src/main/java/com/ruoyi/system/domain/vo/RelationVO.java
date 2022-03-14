package com.ruoyi.system.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年03月14日 17:50
 * @decription: 关系VO
 */
@Data
@Builder
public class RelationVO implements Serializable {

    private String desc;
    private Integer code;

}
