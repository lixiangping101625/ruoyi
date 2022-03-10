package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AdministrationProvinceMapper;
import com.ruoyi.system.domain.AdministrationProvince;
import com.ruoyi.system.service.IAdministrationProvinceService;

/**
 * 省份设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@Service
public class AdministrationProvinceServiceImpl implements IAdministrationProvinceService 
{
    @Autowired
    private AdministrationProvinceMapper administrationProvinceMapper;

    /**
     * 查询省份设置
     * 
     * @param provinceId 省份设置主键
     * @return 省份设置
     */
    @Override
    public AdministrationProvince selectAdministrationProvinceByProvinceId(Long provinceId)
    {
        return administrationProvinceMapper.selectAdministrationProvinceByProvinceId(provinceId);
    }

    /**
     * 查询省份设置列表
     * 
     * @param administrationProvince 省份设置
     * @return 省份设置
     */
    @Override
    public List<AdministrationProvince> selectAdministrationProvinceList(AdministrationProvince administrationProvince)
    {
        return administrationProvinceMapper.selectAdministrationProvinceList(administrationProvince);
    }

    /**
     * 新增省份设置
     * 
     * @param administrationProvince 省份设置
     * @return 结果
     */
    @Override
    public int insertAdministrationProvince(AdministrationProvince administrationProvince)
    {
        return administrationProvinceMapper.insertAdministrationProvince(administrationProvince);
    }

    /**
     * 修改省份设置
     * 
     * @param administrationProvince 省份设置
     * @return 结果
     */
    @Override
    public int updateAdministrationProvince(AdministrationProvince administrationProvince)
    {
        return administrationProvinceMapper.updateAdministrationProvince(administrationProvince);
    }

    /**
     * 批量删除省份设置
     * 
     * @param provinceIds 需要删除的省份设置主键
     * @return 结果
     */
    @Override
    public int deleteAdministrationProvinceByProvinceIds(Long[] provinceIds)
    {
        return administrationProvinceMapper.deleteAdministrationProvinceByProvinceIds(provinceIds);
    }

    /**
     * 删除省份设置信息
     * 
     * @param provinceId 省份设置主键
     * @return 结果
     */
    @Override
    public int deleteAdministrationProvinceByProvinceId(Long provinceId)
    {
        return administrationProvinceMapper.deleteAdministrationProvinceByProvinceId(provinceId);
    }
}
