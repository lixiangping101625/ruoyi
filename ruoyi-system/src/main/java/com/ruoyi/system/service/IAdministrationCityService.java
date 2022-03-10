package com.ruoyi.system.service;

import com.ruoyi.system.domain.AdministrationCity;

import java.util.List;

/**
 * 城市设置Service接口
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
public interface IAdministrationCityService 
{
    /**
     * 查询城市设置
     * 
     * @param cityId 城市设置主键
     * @return 城市设置
     */
    public AdministrationCity selectAdministrationCityByCityId(Long cityId);

    /**
     * 查询城市设置列表
     * 
     * @param administrationCity 城市设置
     * @return 城市设置集合
     */
    public List<AdministrationCity> selectAdministrationCityList(AdministrationCity administrationCity);

    /**
     * 新增城市设置
     * 
     * @param administrationCity 城市设置
     * @return 结果
     */
    public int insertAdministrationCity(AdministrationCity administrationCity);

    /**
     * 修改城市设置
     * 
     * @param administrationCity 城市设置
     * @return 结果
     */
    public int updateAdministrationCity(AdministrationCity administrationCity);

    /**
     * 批量删除城市设置
     * 
     * @param cityIds 需要删除的城市设置主键集合
     * @return 结果
     */
    public int deleteAdministrationCityByCityIds(Long[] cityIds);

    /**
     * 删除城市设置信息
     * 
     * @param cityId 城市设置主键
     * @return 结果
     */
    public int deleteAdministrationCityByCityId(Long cityId);
}
