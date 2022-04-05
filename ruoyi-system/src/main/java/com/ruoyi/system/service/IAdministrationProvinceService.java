package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.AdministrationProvince;

/**
 * 省份设置Service接口
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
public interface IAdministrationProvinceService 
{


    AjaxResult queryServiceAreas();

    /**
     * 查询省份设置
     * 
     * @param provinceId 省份设置主键
     * @return 省份设置
     */
    public AdministrationProvince selectAdministrationProvinceByProvinceId(Long provinceId);

    /**
     * 查询省份设置列表
     * 
     * @param administrationProvince 省份设置
     * @return 省份设置集合
     */
    public List<AdministrationProvince> selectAdministrationProvinceList(AdministrationProvince administrationProvince);

    /**
     * 新增省份设置
     * 
     * @param administrationProvince 省份设置
     * @return 结果
     */
    public int insertAdministrationProvince(AdministrationProvince administrationProvince);

    /**
     * 修改省份设置
     * 
     * @param administrationProvince 省份设置
     * @return 结果
     */
    public int updateAdministrationProvince(AdministrationProvince administrationProvince);

    /**
     * 批量删除省份设置
     * 
     * @param provinceIds 需要删除的省份设置主键集合
     * @return 结果
     */
    public int deleteAdministrationProvinceByProvinceIds(Long[] provinceIds);

    /**
     * 删除省份设置信息
     * 
     * @param provinceId 省份设置主键
     * @return 结果
     */
    public int deleteAdministrationProvinceByProvinceId(Long provinceId);
}
