package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserCoupon;
import com.ruoyi.system.domain.vo.UserCouponVO;

/**
 * 用户优惠券 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface IUserCouponService 
{
    /**
     * 查询用户优惠券 
     * 
     * @param id 用户优惠券 主键
     * @return 用户优惠券 
     */
    public UserCoupon selectUserCouponById(Long id);

    /**
     * 查询用户优惠券 列表
     * 
     * @param userCoupon 用户优惠券 
     * @return 用户优惠券 集合
     */
    public List<UserCouponVO> selectUserCouponList(Integer type);

    /**
     * 新增用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    public AjaxResult insertUserCoupon(UserCoupon userCoupon);

    /**
     * 修改用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    public int updateUserCoupon(UserCoupon userCoupon);

    /**
     * 批量删除用户优惠券 
     * 
     * @param ids 需要删除的用户优惠券 主键集合
     * @return 结果
     */
    public int deleteUserCouponByIds(Long[] ids);

    /**
     * 删除用户优惠券 信息
     * 
     * @param id 用户优惠券 主键
     * @return 结果
     */
    public int deleteUserCouponById(Long id);
}
