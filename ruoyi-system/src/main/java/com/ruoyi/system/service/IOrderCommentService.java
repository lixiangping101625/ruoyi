package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OrderComment;
import com.ruoyi.system.domain.vo.UserCommentVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户点评 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
public interface IOrderCommentService 
{
    /**
     * 查询用户点评 
     * 
     * @param id 用户点评 主键
     * @return 用户点评 
     */
    public OrderComment selectOrderCommentById(Long id);

    /**
     * 查询用户点评 列表
     * 
     * @param orderComment 用户点评 
     * @return 用户点评 集合
     */
    public List<OrderComment> selectOrderCommentList(OrderComment orderComment);


    public List<UserCommentVO> selectList(Long userId);

    /**
     * 新增用户点评 
     * 
     * @param orderComment 用户点评 
     * @return 结果
     */
    public int insertOrderComment(OrderComment orderComment);

    public int addComment(Long orderId,
                          String orderNo,
                          String content,
                          Integer score,
                          Long userId,
                          MultipartFile[] imgs);

    /**
     * 修改用户点评 
     * 
     * @param orderComment 用户点评 
     * @return 结果
     */
    public int updateOrderComment(OrderComment orderComment);

    /**
     * 批量删除用户点评 
     * 
     * @param ids 需要删除的用户点评 主键集合
     * @return 结果
     */
    public int deleteOrderCommentByIds(Long[] ids);

    /**
     * 删除用户点评 信息
     * 
     * @param id 用户点评 主键
     * @return 结果
     */
    public int deleteOrderCommentById(Long id);
}
