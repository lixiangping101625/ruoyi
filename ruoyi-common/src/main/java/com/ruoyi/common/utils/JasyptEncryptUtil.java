package com.ruoyi.common.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author Administrator
 * @descition
 * @date 2022/03/20  18:46
 */
public class JasyptEncryptUtil {
    public static void main(String[] args){
        //PBEWithMD5AndDES
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        //加密的key值，用于配置文件jasypt.encryptor.password的值
        encryptor.setPassword("yuanban_123@!");
        //加密

    }
}