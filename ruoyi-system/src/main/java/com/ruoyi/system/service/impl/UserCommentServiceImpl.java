package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserCommentMapper;
import com.ruoyi.system.domain.UserComment;
import com.ruoyi.system.service.IUserCommentService;

/**
 * 用户点评 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class UserCommentServiceImpl implements IUserCommentService 
{
    @Autowired
    private UserCommentMapper userCommentMapper;

    /**
     * 查询用户点评 
     * 
     * @param id 用户点评 主键
     * @return 用户点评 
     */
    @Override
    public UserComment selectUserCommentById(Long id)
    {
        return userCommentMapper.selectUserCommentById(id);
    }

    /**
     * 查询用户点评 列表
     * 
     * @param userComment 用户点评 
     * @return 用户点评 
     */
    @Override
    public List<UserComment> selectUserCommentList(UserComment userComment)
    {
        return userCommentMapper.selectUserCommentList(userComment);
    }

    @Override
    public List<UserComment> selectUserCommentList()
    {
        return userCommentMapper.selectUserCommentList(SecurityUtils.getUserId());
    }

    /**
     * 新增用户点评 
     * 
     * @param userComment 用户点评 
     * @return 结果
     */
    @Override
    public int insertUserComment(UserComment userComment)
    {
        return userCommentMapper.insertUserComment(userComment);
    }

    /**
     * 修改用户点评 
     * 
     * @param userComment 用户点评 
     * @return 结果
     */
    @Override
    public int updateUserComment(UserComment userComment)
    {
        return userCommentMapper.updateUserComment(userComment);
    }

    /**
     * 批量删除用户点评 
     * 
     * @param ids 需要删除的用户点评 主键
     * @return 结果
     */
    @Override
    public int deleteUserCommentByIds(Long[] ids)
    {
        return userCommentMapper.deleteUserCommentByIds(ids);
    }

    /**
     * 删除用户点评 信息
     * 
     * @param id 用户点评 主键
     * @return 结果
     */
    @Override
    public int deleteUserCommentById(Long id)
    {
        return userCommentMapper.deleteUserCommentById(id);
    }
}
