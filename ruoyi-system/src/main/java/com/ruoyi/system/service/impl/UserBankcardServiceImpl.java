package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserBankcardMapper;
import com.ruoyi.system.domain.UserBankcard;
import com.ruoyi.system.service.IUserBankcardService;

/**
 * 用户银行卡 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class UserBankcardServiceImpl implements IUserBankcardService 
{
    @Autowired
    private UserBankcardMapper userBankcardMapper;

    /**
     * 查询用户银行卡 
     * 
     * @param id 用户银行卡 主键
     * @return 用户银行卡 
     */
    @Override
    public UserBankcard selectUserBankcardById(Long id)
    {
        return userBankcardMapper.selectUserBankcardById(id);
    }

    /**
     * 查询用户银行卡 列表
     * 
     * @param userBankcard 用户银行卡 
     * @return 用户银行卡 
     */
    @Override
    public List<UserBankcard> selectUserBankcardList()
    {
        UserBankcard userBankcard = new UserBankcard();
        userBankcard.setUserId(SecurityUtils.getUserId());
        return userBankcardMapper.selectUserBankcardList(userBankcard);
    }

    /**
     * 新增用户银行卡 
     * 
     * @param userBankcard 用户银行卡 
     * @return 结果
     */
    @Override
    public int insertUserBankcard(UserBankcard userBankcard)
    {
        userBankcard.setId(SnowflakeUtils.nextId());
        userBankcard.setUserId(SecurityUtils.getUserId());
        userBankcard.setCreatedBy(SecurityUtils.getUserId().toString());
        userBankcard.setCreatedTime(DateUtils.getNowDate());
        return userBankcardMapper.insertUserBankcard(userBankcard);
    }

    /**
     * 修改用户银行卡 
     * 
     * @param userBankcard 用户银行卡 
     * @return 结果
     */
    @Override
    public AjaxResult updateUserBankcard(UserBankcard userBankcard)
    {
        Long userId = SecurityUtils.getUserId();
        //判断银行卡是否存在
        UserBankcard userBankcardDB = userBankcardMapper.selectUserBankcardById(userBankcard.getId());
        if (userBankcardDB == null) {
            return AjaxResult.error("银行卡不存在~");
        }
        if (!userBankcardDB.getUserId().equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("不是当前持卡人~");
        }
        userBankcard.setUserId(userId);
        userBankcard.setUpdatedTime(DateUtils.getNowDate());
        userBankcard.setUpdatedBy(userId.toString());

        return userBankcardMapper.updateUserBankcard(userBankcard)>0 ? AjaxResult.success("修改成功"):AjaxResult.error("修改失败");
    }

    /**
     * 批量删除用户银行卡 
     * 
     * @param ids 需要删除的用户银行卡 主键
     * @return 结果
     */
    @Override
    public int deleteUserBankcardByIds(Long[] ids)
    {
        return userBankcardMapper.deleteUserBankcardByIds(ids);
    }

    /**
     * 删除用户银行卡 信息
     * 
     * @param id 用户银行卡 主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteUserBankcardById(Long id)
    {
        //判断银行卡是否存在
        UserBankcard userBankcard = userBankcardMapper.selectUserBankcardById(id);
        if (userBankcard == null) {
            return AjaxResult.error("银行卡不存在~");
        }
        if (!userBankcard.getUserId().equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("不是当前持卡人~");
        }
        return userBankcardMapper.deleteUserBankcardById(id)>0 ? AjaxResult.success("删除成功~"):AjaxResult.error("删除失败~");
    }
}
