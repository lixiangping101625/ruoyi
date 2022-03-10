package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SwiperMapper;
import com.ruoyi.system.domain.Swiper;
import com.ruoyi.system.service.ISwiperService;

/**
 * 轮播图;轮播图Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-10
 */
@Service
public class SwiperServiceImpl implements ISwiperService
{
    @Autowired
    private SwiperMapper swiperMapper;

    /**
     * 查询轮播图;轮播图
     *
     * @param id 轮播图;轮播图主键
     * @return 轮播图;轮播图
     */
    @Override
    public Swiper selectSwiperById(Long id)
    {
        return swiperMapper.selectSwiperById(id);
    }

    /**
     * 查询轮播图;轮播图列表
     *
     * @param swiper 轮播图;轮播图
     * @return 轮播图;轮播图
     */
    @Override
    public List<Swiper> selectSwiperList(Swiper swiper)
    {
        return swiperMapper.selectSwiperList(swiper);
    }

    /**
     * 新增轮播图;轮播图
     *
     * @param swiper 轮播图;轮播图
     * @return 结果
     */
    @Override
    public int insertSwiper(Swiper swiper)
    {
        return swiperMapper.insertSwiper(swiper);
    }

    /**
     * 修改轮播图;轮播图
     *
     * @param swiper 轮播图;轮播图
     * @return 结果
     */
    @Override
    public int updateSwiper(Swiper swiper)
    {
        return swiperMapper.updateSwiper(swiper);
    }

    /**
     * 批量删除轮播图;轮播图
     *
     * @param ids 需要删除的轮播图;轮播图主键
     * @return 结果
     */
    @Override
    public int deleteSwiperByIds(Long[] ids)
    {
        return swiperMapper.deleteSwiperByIds(ids);
    }

    /**
     * 删除轮播图;轮播图信息
     *
     * @param id 轮播图;轮播图主键
     * @return 结果
     */
    @Override
    public int deleteSwiperById(Long id)
    {
        return swiperMapper.deleteSwiperById(id);
    }
}
