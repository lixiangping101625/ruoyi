package com.ruoyi.common.utils.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 21:53
 * @decription: 自定义PageInfo：简化
 */
@Data
public class CustomPageInfo<T> implements Serializable {

    private int pageNum;
    private int pageSize;
    private int size;//当前页数量
    private List<T> list;
    private int pages;//总页数

}
