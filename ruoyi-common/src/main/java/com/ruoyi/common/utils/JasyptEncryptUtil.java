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
        System.out.println(encryptor.encrypt("LTAI5t7HFykTPfDUqKuzGXL4"));
        System.out.println(encryptor.encrypt("oa8XKDNxB4P2bQgM7ETwft0pB6KPSS"));
//        System.out.println(encryptor.encrypt("fojing_username"));
//        System.out.println(encryptor.encrypt("Fojing1234"));
//
        //解密
        /*BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("key");
        String decrypted = encryptor.decrypt("密文");
        System.out.println(decrypted);*/
    }
}