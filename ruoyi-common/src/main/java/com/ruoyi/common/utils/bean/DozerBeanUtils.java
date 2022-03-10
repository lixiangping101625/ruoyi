package com.ruoyi.common.utils.bean;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 20:48
 * @decription:
 */
public class DozerBeanUtils {

    /**
     * 深度拷贝
     * @param object
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T deepCopy(Object object, Class<T> tClass){
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        T t = mapper.map(object, tClass);
        return t;
    }


}
