package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和就诊人关系枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum RelationEnum implements Serializable {

    PARENTS(1, "父母"),
    CHILDREN(2, "子女"),
    BROTHERS_SISTERS(3, "兄弟姐妹"),
    HANSBAND_WIFE(4, "夫妻"),
    MYSELF(5, "本人"),
    OTHER(6, "其他");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code){
        RelationEnum[] values = RelationEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].code == code) {
                return values[i].desc;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
