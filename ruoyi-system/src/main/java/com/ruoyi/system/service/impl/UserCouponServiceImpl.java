package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.constant.DataStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.vo.UserCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserCouponMapper;
import com.ruoyi.system.domain.UserCoupon;
import com.ruoyi.system.service.IUserCouponService;

/**
 * 用户优惠券 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class UserCouponServiceImpl implements IUserCouponService 
{
    @Autowired
    private UserCouponMapper userCouponMapper;

    /**
     * 查询用户优惠券 
     * 
     * @param id 用户优惠券 主键
     * @return 用户优惠券 
     */
    @Override
    public UserCoupon selectUserCouponById(Long id)
    {
        return userCouponMapper.selectUserCouponById(id);
    }

    /**
     * 查询用户优惠券 列表
     * 
     * @param userCoupon 用户优惠券 
     * @return 用户优惠券 
     */
    @Override
    public List<UserCouponVO> selectUserCouponList(Integer type)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", SecurityUtils.getUserId());
        map.put("type", type);
        return userCouponMapper.selectCouponList(map);
    }

    /**
     * 新增用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    @Override
    public AjaxResult insertUserCoupon(UserCoupon userCoupon)
    {
        Date date = new Date();
        Long userId = SecurityUtils.getUserId();

        //判断是否已领取
        UserCoupon selectDomain = new UserCoupon();
        selectDomain.setUserId(userId);
        selectDomain.setCouponId(userCoupon.getCouponId());
        List<UserCoupon> userCoupons = userCouponMapper.selectUserCouponList(selectDomain);
        if (userCoupons.size() > 0) {
            return AjaxResult.error("不可重复领取~");
        }
        //领取
        userCoupon.setId(SnowflakeUtils.nextId());
        userCoupon.setUserId(userId);
        userCoupon.setCreatedTime(date);
        userCoupon.setCreatedBy(userId.toString());
        userCoupon.setUsingStatus(DataStatus.NORMAL);
        return userCouponMapper.insertUserCoupon(userCoupon)>0 ? AjaxResult.success("领取成功~"):AjaxResult.error("领取失败~");
    }

    /**
     * 修改用户优惠券 
     * 
     * @param userCoupon 用户优惠券 
     * @return 结果
     */
    @Override
    public int updateUserCoupon(UserCoupon userCoupon)
    {
        return userCouponMapper.updateUserCoupon(userCoupon);
    }

    /**
     * 批量删除用户优惠券 
     * 
     * @param ids 需要删除的用户优惠券 主键
     * @return 结果
     */
    @Override
    public int deleteUserCouponByIds(Long[] ids)
    {
        return userCouponMapper.deleteUserCouponByIds(ids);
    }

    /**
     * 删除用户优惠券 信息
     * 
     * @param id 用户优惠券 主键
     * @return 结果
     */
    @Override
    public int deleteUserCouponById(Long id)
    {
        return userCouponMapper.deleteUserCouponById(id);
    }
}
