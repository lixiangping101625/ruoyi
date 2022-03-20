package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * OSS文件枚举类
 * 
 * @author ruoyi
 */
@AllArgsConstructor
@NoArgsConstructor
public enum  OSSFileEnum
{
    USER_BASE(100,"user-data","用户数据基本目录"),
    USER_AVATAR(101,"user-avatar","用户头像子目录"),
    USER_COMMENT(102,"user-comment","用户点评子目录");

//    SYS_BASE(200,"sys-base","系统数据基本目录"),
//    SYS_ICON(201,"sys-icon","系统图标子目录"),
//    SYS_IMG(202,"sys-img","稀土图片子目录");

    private Integer code;
    private String direct;//目录
    private String desc;//描述

    public static OSSFileEnum convertCode2Instance(Integer code){
        OSSFileEnum[] values = OSSFileEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getCode()==code){
                return values[i];
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDirect() {
        return direct;
    }

    public String getDesc() {
        return desc;
    }
}
