package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserStoregold;

/**
 * 用户储值金 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface IUserStoregoldService 
{
    /**
     * 查询用户储值金 
     * 
     * @param id 用户储值金 主键
     * @return 用户储值金 
     */
    public UserStoregold selectUserStoregoldById(Long id);

    /**
     * 查询用户储值金 列表
     * 
     * @param userStoregold 用户储值金 
     * @return 用户储值金 集合
     */
    public List<UserStoregold> selectUserStoregoldList(UserStoregold userStoregold);

    /**
     * 新增用户储值金 
     * 
     * @param userStoregold 用户储值金 
     * @return 结果
     */
    public int insertUserStoregold(UserStoregold userStoregold);

    /**
     * 修改用户储值金 
     * 
     * @param userStoregold 用户储值金 
     * @return 结果
     */
    public int updateUserStoregold(UserStoregold userStoregold);

    /**
     * 批量删除用户储值金 
     * 
     * @param ids 需要删除的用户储值金 主键集合
     * @return 结果
     */
    public int deleteUserStoregoldByIds(Long[] ids);

    /**
     * 删除用户储值金 信息
     * 
     * @param id 用户储值金 主键
     * @return 结果
     */
    public int deleteUserStoregoldById(Long id);

    AjaxResult getCurrAmount();
}
