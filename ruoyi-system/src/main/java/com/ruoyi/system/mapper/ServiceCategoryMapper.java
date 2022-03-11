package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ServiceCategory;

/**
 * （商品）服务类目;服务类目Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
public interface ServiceCategoryMapper 
{
    /**
     * 查询（商品）服务类目;服务类目
     * 
     * @param id （商品）服务类目;服务类目主键
     * @return （商品）服务类目;服务类目
     */
    public ServiceCategory selectServiceCategoryById(Long id);

    /**
     * 查询（商品）服务类目;服务类目列表
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return （商品）服务类目;服务类目集合
     */
    public List<ServiceCategory> selectServiceCategoryList(ServiceCategory serviceCategory);

    /**
     * 新增（商品）服务类目;服务类目
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return 结果
     */
    public int insertServiceCategory(ServiceCategory serviceCategory);

    /**
     * 修改（商品）服务类目;服务类目
     * 
     * @param serviceCategory （商品）服务类目;服务类目
     * @return 结果
     */
    public int updateServiceCategory(ServiceCategory serviceCategory);

    /**
     * 删除（商品）服务类目;服务类目
     * 
     * @param id （商品）服务类目;服务类目主键
     * @return 结果
     */
    public int deleteServiceCategoryById(Long id);

    /**
     * 批量删除（商品）服务类目;服务类目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceCategoryByIds(Long[] ids);
}
