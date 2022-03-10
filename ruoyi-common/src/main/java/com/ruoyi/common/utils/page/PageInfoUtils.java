package com.ruoyi.common.utils.page;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.bean.DozerBeanUtils;

import java.util.List;


/**
 * @author Lixiangping
 * @createTime 2022年03月10日 21:47
 * @decription: 分页查询
 */
public class PageInfoUtils<T> {

//    public static <T> PageInfo<T> wrapperData(List<T> list) {
//        return new PageInfo(list);
//    }

    public static <T> CustomPageInfo<T> wrapperData(List<T> list) {
        PageInfo pageInfo = new PageInfo(list);

        CustomPageInfo customPageInfo = DozerBeanUtils.deepCopy(pageInfo, CustomPageInfo.class);

        return customPageInfo;
    }

}
