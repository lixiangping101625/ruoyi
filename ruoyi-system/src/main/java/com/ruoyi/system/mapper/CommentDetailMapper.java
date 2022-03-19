package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CommentDetail;

/**
 * 用户点评媒体 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
public interface CommentDetailMapper 
{
    /**
     * 查询用户点评媒体 
     * 
     * @param id 用户点评媒体 主键
     * @return 用户点评媒体 
     */
    public CommentDetail selectCommentDetailById(Long id);

    /**
     * 查询用户点评媒体 列表
     * 
     * @param commentDetail 用户点评媒体 
     * @return 用户点评媒体 集合
     */
    public List<CommentDetail> selectCommentDetailList(CommentDetail commentDetail);

    /**
     * 新增用户点评媒体 
     * 
     * @param commentDetail 用户点评媒体 
     * @return 结果
     */
    public int insertCommentDetail(CommentDetail commentDetail);

    /**
     * 修改用户点评媒体 
     * 
     * @param commentDetail 用户点评媒体 
     * @return 结果
     */
    public int updateCommentDetail(CommentDetail commentDetail);

    /**
     * 删除用户点评媒体 
     * 
     * @param id 用户点评媒体 主键
     * @return 结果
     */
    public int deleteCommentDetailById(Long id);

    /**
     * 批量删除用户点评媒体 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentDetailByIds(Long[] ids);
}
