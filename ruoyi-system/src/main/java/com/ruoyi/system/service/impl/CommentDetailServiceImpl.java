package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommentDetailMapper;
import com.ruoyi.system.domain.CommentDetail;
import com.ruoyi.system.service.ICommentDetailService;

/**
 * 用户点评媒体 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@Service
public class CommentDetailServiceImpl implements ICommentDetailService 
{
    @Autowired
    private CommentDetailMapper commentDetailMapper;

    /**
     * 查询用户点评媒体 
     * 
     * @param id 用户点评媒体 主键
     * @return 用户点评媒体 
     */
    @Override
    public CommentDetail selectCommentDetailById(Long id)
    {
        return commentDetailMapper.selectCommentDetailById(id);
    }

    /**
     * 查询用户点评媒体 列表
     * 
     * @param commentDetail 用户点评媒体 
     * @return 用户点评媒体 
     */
    @Override
    public List<CommentDetail> selectCommentDetailList(CommentDetail commentDetail)
    {
        return commentDetailMapper.selectCommentDetailList(commentDetail);
    }

    /**
     * 新增用户点评媒体 
     * 
     * @param commentDetail 用户点评媒体 
     * @return 结果
     */
    @Override
    public int insertCommentDetail(CommentDetail commentDetail)
    {
        return commentDetailMapper.insertCommentDetail(commentDetail);
    }

    /**
     * 修改用户点评媒体 
     * 
     * @param commentDetail 用户点评媒体 
     * @return 结果
     */
    @Override
    public int updateCommentDetail(CommentDetail commentDetail)
    {
        return commentDetailMapper.updateCommentDetail(commentDetail);
    }

    /**
     * 批量删除用户点评媒体 
     * 
     * @param ids 需要删除的用户点评媒体 主键
     * @return 结果
     */
    @Override
    public int deleteCommentDetailByIds(Long[] ids)
    {
        return commentDetailMapper.deleteCommentDetailByIds(ids);
    }

    /**
     * 删除用户点评媒体 信息
     * 
     * @param id 用户点评媒体 主键
     * @return 结果
     */
    @Override
    public int deleteCommentDetailById(Long id)
    {
        return commentDetailMapper.deleteCommentDetailById(id);
    }
}
