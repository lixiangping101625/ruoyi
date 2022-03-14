package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.constant.ExchangeConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.vo.UserStoregoldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserStoregoldMapper;
import com.ruoyi.system.domain.UserStoregold;
import com.ruoyi.system.service.IUserStoregoldService;

/**
 * 用户储值金 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class UserStoregoldServiceImpl implements IUserStoregoldService 
{
    @Autowired
    private UserStoregoldMapper userStoregoldMapper;

    /**
     * 查询用户储值金 
     * 
     * @param id 用户储值金 主键
     * @return 用户储值金 
     */
    @Override
    public UserStoregold selectUserStoregoldById(Long id)
    {
        return userStoregoldMapper.selectUserStoregoldById(id);
    }

    /**
     * 查询用户储值金 列表
     * 
     * @param userStoregold 用户储值金 
     * @return 用户储值金 
     */
    @Override
    public List<UserStoregold> selectUserStoregoldList(UserStoregold userStoregold)
    {
        userStoregold.setUserId(SecurityUtils.getUserId());
        return userStoregoldMapper.selectUserStoregoldList(userStoregold);
    }

    /**
     * 新增用户储值金 
     * 
     * @param userStoregold 用户储值金 
     * @return 结果
     */
    @Override
    public int insertUserStoregold(UserStoregold userStoregold)
    {
        Long userId = SecurityUtils.getUserId();
        //查询最新交易
        UserStoregold latest = userStoregoldMapper.queryLatest(userId);

        //当前储值金
        BigDecimal currAmount = userStoregold.getExchangeAmount();
        if (latest!=null) {//有历史交易记录
            if (userStoregold.getExchangeType()== ExchangeConstants.CONSUME){
                currAmount = latest.getCurrAmount().subtract(userStoregold.getExchangeAmount());
            }
            else if (userStoregold.getExchangeType()== ExchangeConstants.CHARGE){
                currAmount = latest.getCurrAmount().add(userStoregold.getExchangeAmount());
            }
        }
        //组装
        userStoregold.setId(SnowflakeUtils.nextId());
        userStoregold.setCurrAmount(currAmount);
        userStoregold.setUserId(userId);
        userStoregold.setCreatedTime(DateUtils.getNowDate());
        userStoregold.setCreatedBy(userId.toString());

        return userStoregoldMapper.insertUserStoregold(userStoregold);
    }

    /**
     * 修改用户储值金 
     * 
     * @param userStoregold 用户储值金 
     * @return 结果
     */
    @Override
    public int updateUserStoregold(UserStoregold userStoregold)
    {
        return userStoregoldMapper.updateUserStoregold(userStoregold);
    }

    /**
     * 批量删除用户储值金 
     * 
     * @param ids 需要删除的用户储值金 主键
     * @return 结果
     */
    @Override
    public int deleteUserStoregoldByIds(Long[] ids)
    {
        return userStoregoldMapper.deleteUserStoregoldByIds(ids);
    }

    /**
     * 删除用户储值金 信息
     * 
     * @param id 用户储值金 主键
     * @return 结果
     */
    @Override
    public int deleteUserStoregoldById(Long id)
    {
        return userStoregoldMapper.deleteUserStoregoldById(id);
    }

    @Override
    public AjaxResult getCurrAmount() {
        UserStoregold userStoregold = userStoregoldMapper.queryLatest(SecurityUtils.getUserId());
        UserStoregoldVO userStoregoldVO = DozerBeanUtils.deepCopy(userStoregold, UserStoregoldVO.class);
        return AjaxResult.success(userStoregoldVO);
    }
}
