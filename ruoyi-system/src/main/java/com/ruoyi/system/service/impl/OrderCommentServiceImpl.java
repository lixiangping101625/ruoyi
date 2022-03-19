package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.UserCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderCommentMapper;
import com.ruoyi.system.domain.OrderComment;
import com.ruoyi.system.service.IOrderCommentService;

/**
 * 用户点评 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@Service
public class OrderCommentServiceImpl implements IOrderCommentService 
{
    @Autowired
    private OrderCommentMapper orderCommentMapper;

    /**
     * 查询用户点评 
     * 
     * @param id 用户点评 主键
     * @return 用户点评 
     */
    @Override
    public OrderComment selectOrderCommentById(Long id)
    {
        return orderCommentMapper.selectOrderCommentById(id);
    }

    /**
     * 查询用户点评 列表
     * 
     * @param orderComment 用户点评 
     * @return 用户点评 
     */
    @Override
    public List<OrderComment> selectOrderCommentList(OrderComment orderComment)
    {
        return orderCommentMapper.selectOrderCommentList(orderComment);
    }

    @Override
    public List<UserCommentVO> selectList(Long userId) {

        return orderCommentMapper.selectList(userId);
    }

    /**
     * 新增用户点评 
     * 
     * @param orderComment 用户点评 
     * @return 结果
     */
    @Override
    public int insertOrderComment(OrderComment orderComment)
    {
        return orderCommentMapper.insertOrderComment(orderComment);
    }

    /**
     * 修改用户点评 
     * 
     * @param orderComment 用户点评 
     * @return 结果
     */
    @Override
    public int updateOrderComment(OrderComment orderComment)
    {
        return orderCommentMapper.updateOrderComment(orderComment);
    }

    /**
     * 批量删除用户点评 
     * 
     * @param ids 需要删除的用户点评 主键
     * @return 结果
     */
    @Override
    public int deleteOrderCommentByIds(Long[] ids)
    {
        return orderCommentMapper.deleteOrderCommentByIds(ids);
    }

    /**
     * 删除用户点评 信息
     * 
     * @param id 用户点评 主键
     * @return 结果
     */
    @Override
    public int deleteOrderCommentById(Long id)
    {
        return orderCommentMapper.deleteOrderCommentById(id);
    }
}
