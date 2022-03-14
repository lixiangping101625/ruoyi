package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserStoregold;

/**
 * 用户储值金 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface UserStoregoldMapper 
{
    /**
     * 查询最新交易记录
     * @param userId
     * @return
     */
    UserStoregold queryLatest(Long userId);

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
     * 删除用户储值金 
     * 
     * @param id 用户储值金 主键
     * @return 结果
     */
    public int deleteUserStoregoldById(Long id);

    /**
     * 批量删除用户储值金 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserStoregoldByIds(Long[] ids);
}
