package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ServiceGoodsMapper;
import com.ruoyi.system.domain.ServiceGoods;
import com.ruoyi.system.service.IServiceGoodsService;

/**
 * （商品）服务定价;Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@Service
public class ServiceGoodsServiceImpl implements IServiceGoodsService 
{
    @Autowired
    private ServiceGoodsMapper serviceGoodsMapper;

    /**
     * 查询（商品）服务定价;
     * 
     * @param id （商品）服务定价;主键
     * @return （商品）服务定价;
     */
    @Override
    public ServiceGoods selectServiceGoodsById(Long id)
    {
        return serviceGoodsMapper.selectServiceGoodsById(id);
    }

    /**
     * 查询（商品）服务定价;列表
     * 
     * @param serviceGoods （商品）服务定价;
     * @return （商品）服务定价;
     */
    @Override
    public List<ServiceGoods> selectServiceGoodsList(ServiceGoods serviceGoods)
    {
        return serviceGoodsMapper.selectServiceGoodsList(serviceGoods);
    }

    /**
     * 新增（商品）服务定价;
     * 
     * @param serviceGoods （商品）服务定价;
     * @return 结果
     */
    @Override
    public int insertServiceGoods(ServiceGoods serviceGoods)
    {
        return serviceGoodsMapper.insertServiceGoods(serviceGoods);
    }

    /**
     * 修改（商品）服务定价;
     * 
     * @param serviceGoods （商品）服务定价;
     * @return 结果
     */
    @Override
    public int updateServiceGoods(ServiceGoods serviceGoods)
    {
        return serviceGoodsMapper.updateServiceGoods(serviceGoods);
    }

    /**
     * 批量删除（商品）服务定价;
     * 
     * @param ids 需要删除的（商品）服务定价;主键
     * @return 结果
     */
    @Override
    public int deleteServiceGoodsByIds(Long[] ids)
    {
        return serviceGoodsMapper.deleteServiceGoodsByIds(ids);
    }

    /**
     * 删除（商品）服务定价;信息
     * 
     * @param id （商品）服务定价;主键
     * @return 结果
     */
    @Override
    public int deleteServiceGoodsById(Long id)
    {
        return serviceGoodsMapper.deleteServiceGoodsById(id);
    }
}
