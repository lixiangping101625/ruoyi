package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 银行枚举类
 */
@NoArgsConstructor
@AllArgsConstructor
public enum BankEnum implements Serializable {

    AGRICULTURAL(1, "中国农业银行"),
    MERCHANTS(2, "招商银行"),
    CONSTRUCTION(3, "中国建设银行"),
    INDUSTRIAL_COMMERCIAL(4, "中国工商银行"),
    COMMUNICATIONS(6, "交通银行");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code){
        BankEnum[] values = BankEnum.values();
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
