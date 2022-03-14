package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.UserCoupon;
import com.ruoyi.system.domain.vo.UserCouponVO;
import org.springframework.data.repository.query.Param;

/**
 * 用户优惠券 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface UserCouponMapper 
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
    public List<UserCoupon> selectUserCouponList(UserCoupon userCoupon);

    /**
     * 新增用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    public int insertUserCoupon(UserCoupon userCoupon);

    /**
     * 修改用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    public int updateUserCoupon(UserCoupon userCoupon);

    /**
     * 删除用户优惠券 
     * 
     * @param id 用户优惠券 主键
     * @return 结果
     */
    public int deleteUserCouponById(Long id);

    /**
     * 批量删除用户优惠券 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCouponByIds(Long[] ids);

    List<UserCouponVO> selectCouponList(@Param(value = "params") Map params);
}
