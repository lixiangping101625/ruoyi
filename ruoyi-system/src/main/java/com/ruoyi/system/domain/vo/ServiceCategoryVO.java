package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.ServiceGoods;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * （商品）服务类目;服务类目对象 service_category
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@Data
public class ServiceCategoryVO implements Serializable
{
    private Long id;
    /** 类目编码:1-陪诊2-增值 */
    private Long categoryCode;
    /** 服务名称 */
    private String name;
    /** 菜单级别：1-一级2-二级 */
    private Integer menuLevel;
    /** 图标 */
    private String icon;
    private List<ServiceGoodsVO> services;

}