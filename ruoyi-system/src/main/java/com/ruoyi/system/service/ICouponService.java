package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Coupon;

/**
 * 优惠券 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface ICouponService 
{
    /**
     * 查询优惠券 
     * 
     * @param id 优惠券 主键
     * @return 优惠券 
     */
    public Coupon selectCouponById(Long id);

    /**
     * 查询优惠券 列表
     * 
     * @param coupon 优惠券 
     * @return 优惠券 集合
     */
    public List<Coupon> selectCouponList(Coupon coupon);

    /**
     * 查询在线优惠券
     * @return
     */
    public List<Coupon> selectAvailableList();

    /**
     * 新增优惠券 
     * 
     * @param coupon 优惠券 
     * @return 结果
     */
    public int insertCoupon(Coupon coupon);

    /**
     * 修改优惠券 
     * 
     * @param coupon 优惠券 
     * @return 结果
     */
    public int updateCoupon(Coupon coupon);

    /**
     * 批量删除优惠券 
     * 
     * @param ids 需要删除的优惠券 主键集合
     * @return 结果
     */
    public int deleteCouponByIds(Long[] ids);

    /**
     * 删除优惠券 信息
     * 
     * @param id 优惠券 主键
     * @return 结果
     */
    public int deleteCouponById(Long id);
}
