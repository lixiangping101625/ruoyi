package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ServiceCategoryMapper;
import com.ruoyi.system.domain.ServiceCategory;
import com.ruoyi.system.service.IServiceCategoryService;

/**
 * （商品）服务类目;服务类目Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@Service
public class ServiceCategoryServiceImpl implements IServiceCategoryService 
{
    @Autowired
    private ServiceCategoryMapper serviceCategoryMapper;

    /**
     * 查询（商品）服务类目;服务类目
     * 
     * @param id （商品）服务类目;服务类目主键
     * @return （商品）服务类目;服务类目
     */
    @Override
    public ServiceCategory selectServiceCategoryById(Long id)
    {
        return serviceCategoryMapper.selectServiceCategoryById(id);
    }

    /**
     * 查询（商品）服务类目;服务类目列表
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return （商品）服务类目;服务类目
     */
    @Override
    public List<ServiceCategory> selectServiceCategoryList(ServiceCategory serviceCategory)
    {
        return serviceCategoryMapper.selectServiceCategoryList(serviceCategory);
    }

    /**
     * 新增（商品）服务类目;服务类目
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return 结果
     */
    @Override
    public int insertServiceCategory(ServiceCategory serviceCategory)
    {
        return serviceCategoryMapper.insertServiceCategory(serviceCategory);
    }

    /**
     * 修改（商品）服务类目;服务类目
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return 结果
     */
    @Override
    public int updateServiceCategory(ServiceCategory serviceCategory)
    {
        return serviceCategoryMapper.updateServiceCategory(serviceCategory);
    }

    /**
     * 批量删除（商品）服务类目;服务类目
     * 
     * @param ids 需要删除的（商品）服务类目;服务类目主键
     * @return 结果
     */
    @Override
    public int deleteServiceCategoryByIds(Long[] ids)
    {
        return serviceCategoryMapper.deleteServiceCategoryByIds(ids);
    }

    /**
     * 删除（商品）服务类目;服务类目信息
     * 
     * @param id （商品）服务类目;服务类目主键
     * @return 结果
     */
    @Override
    public int deleteServiceCategoryById(Long id)
    {
        return serviceCategoryMapper.deleteServiceCategoryById(id);
    }
}
