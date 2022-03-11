package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ServiceDetail;

/**
 * （商品）服务定价;Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
public interface ServiceGoodsMapper 
{
    /**
     * 查询（商品）服务定价;
     * 
     * @param id （商品）服务定价;主键
     * @return （商品）服务定价;
     */
    public ServiceDetail selectServiceGoodsById(Long id);

    /**
     * 查询（商品）服务定价;列表
     * 
     * @param serviceGoods （商品）服务定价;
     * @return （商品）服务定价;集合
     */
    public List<ServiceDetail> selectServiceGoodsList(ServiceDetail serviceGoods);

    /**
     * 新增（商品）服务定价;
     * 
     * @param serviceGoods （商品）服务定价;
     * @return 结果
     */
    public int insertServiceGoods(ServiceDetail serviceGoods);

    /**
     * 修改（商品）服务定价;
     * 
     * @param serviceGoods （商品）服务定价;
     * @return 结果
     */
    public int updateServiceGoods(ServiceDetail serviceGoods);

    /**
     * 删除（商品）服务定价;
     * 
     * @param id （商品）服务定价;主键
     * @return 结果
     */
    public int deleteServiceGoodsById(Long id);

    /**
     * 批量删除（商品）服务定价;
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceGoodsByIds(Long[] ids);
}
