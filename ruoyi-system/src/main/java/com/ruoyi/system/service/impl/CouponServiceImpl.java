package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CouponMapper;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;

/**
 * 优惠券 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class CouponServiceImpl implements ICouponService 
{
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 查询优惠券 
     * 
     * @param id 优惠券 主键
     * @return 优惠券 
     */
    @Override
    public Coupon selectCouponById(Long id)
    {
        return couponMapper.selectCouponById(id);
    }

    /**
     * 查询优惠券 列表
     * 
     * @param coupon 优惠券 
     * @return 优惠券 
     */
    @Override
    public List<Coupon> selectCouponList(Coupon coupon)
    {
        return couponMapper.selectCouponList(coupon);
    }


    @Override
    public List<Coupon> selectAvailableList()
    {
        return couponMapper.selectAvailableList();
    }

    /**
     * 新增优惠券 
     * 
     * @param coupon 优惠券 
     * @return 结果
     */
    @Override
    public int insertCoupon(Coupon coupon)
    {
        return couponMapper.insertCoupon(coupon);
    }

    /**
     * 修改优惠券 
     * 
     * @param coupon 优惠券 
     * @return 结果
     */
    @Override
    public int updateCoupon(Coupon coupon)
    {
        return couponMapper.updateCoupon(coupon);
    }

    /**
     * 批量删除优惠券 
     * 
     * @param ids 需要删除的优惠券 主键
     * @return 结果
     */
    @Override
    public int deleteCouponByIds(Long[] ids)
    {
        return couponMapper.deleteCouponByIds(ids);
    }

    /**
     * 删除优惠券 信息
     * 
     * @param id 优惠券 主键
     * @return 结果
     */
    @Override
    public int deleteCouponById(Long id)
    {
        return couponMapper.deleteCouponById(id);
    }
}
