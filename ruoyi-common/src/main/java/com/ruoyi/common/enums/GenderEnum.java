package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 性别枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum GenderEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code){
        GenderEnum[] values = GenderEnum.values();
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
