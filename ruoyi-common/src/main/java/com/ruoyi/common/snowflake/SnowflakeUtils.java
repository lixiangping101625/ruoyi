package com.ruoyi.common.snowflake;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 13:59
 * @decription:
 */
public class SnowflakeUtils {

    static SnowFlake snowFlake = new SnowFlake(1, 1);

    public static Long nextId(){
        return snowFlake.nextId();
    }

}
