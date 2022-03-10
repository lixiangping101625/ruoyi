package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.AdministrationCity;
import com.ruoyi.system.mapper.AdministrationCityMapper;
import com.ruoyi.system.service.IAdministrationCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@Service
public class AdministrationCityServiceImpl implements IAdministrationCityService
{
    @Autowired
    private AdministrationCityMapper administrationCityMapper;

    /**
     * 查询城市设置
     * 
     * @param cityId 城市设置主键
     * @return 城市设置
     */
    @Override
    public AdministrationCity selectAdministrationCityByCityId(Long cityId)
    {
        return administrationCityMapper.selectAdministrationCityByCityId(cityId);
    }

    /**
     * 查询城市设置列表
     * 
     * @param administrationCity 城市设置
     * @return 城市设置
     */
    @Override
    public List<AdministrationCity> selectAdministrationCityList(AdministrationCity administrationCity)
    {
        return administrationCityMapper.selectAdministrationCityList(administrationCity);
    }

    /**
     * 新增城市设置
     * 
     * @param administrationCity 城市设置
     * @return 结果
     */
    @Override
    public int insertAdministrationCity(AdministrationCity administrationCity)
    {
        return administrationCityMapper.insertAdministrationCity(administrationCity);
    }

    /**
     * 修改城市设置
     * 
     * @param administrationCity 城市设置
     * @return 结果
     */
    @Override
    public int updateAdministrationCity(AdministrationCity administrationCity)
    {
        return administrationCityMapper.updateAdministrationCity(administrationCity);
    }

    /**
     * 批量删除城市设置
     * 
     * @param cityIds 需要删除的城市设置主键
     * @return 结果
     */
    @Override
    public int deleteAdministrationCityByCityIds(Long[] cityIds)
    {
        return administrationCityMapper.deleteAdministrationCityByCityIds(cityIds);
    }

    /**
     * 删除城市设置信息
     * 
     * @param cityId 城市设置主键
     * @return 结果
     */
    @Override
    public int deleteAdministrationCityByCityId(Long cityId)
    {
        return administrationCityMapper.deleteAdministrationCityByCityId(cityId);
    }
}
