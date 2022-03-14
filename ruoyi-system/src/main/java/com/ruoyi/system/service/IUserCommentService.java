package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserComment;

/**
 * 用户点评 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface IUserCommentService 
{
    /**
     * 查询用户点评 
     * 
     * @param id 用户点评 主键
     * @return 用户点评 
     */
    public UserComment selectUserCommentById(Long id);

    /**
     * 查询用户点评 列表
     * 
     * @param userComment 用户点评 
     * @return 用户点评 集合
     */
    public List<UserComment> selectUserCommentList(UserComment userComment);
    public List<UserComment> selectUserCommentList();

    /**
     * 新增用户点评 
     * 
     * @param userComment 用户点评 
     * @return 结果
     */
    public int insertUserComment(UserComment userComment);

    /**
     * 修改用户点评 
     * 
     * @param userComment 用户点评 
     * @return 结果
     */
    public int updateUserComment(UserComment userComment);

    /**
     * 批量删除用户点评 
     * 
     * @param ids 需要删除的用户点评 主键集合
     * @return 结果
     */
    public int deleteUserCommentByIds(Long[] ids);

    /**
     * 删除用户点评 信息
     * 
     * @param id 用户点评 主键
     * @return 结果
     */
    public int deleteUserCommentById(Long id);
}
