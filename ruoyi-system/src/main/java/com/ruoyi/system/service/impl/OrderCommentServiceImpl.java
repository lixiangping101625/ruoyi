package com.ruoyi.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.CommentDetail;
import com.ruoyi.system.domain.vo.UserCommentVO;
import com.ruoyi.system.mapper.CommentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderCommentMapper;
import com.ruoyi.system.domain.OrderComment;
import com.ruoyi.system.service.IOrderCommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    @Resource
    private CommentDetailMapper commentDetailMapper;

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
        List<UserCommentVO> list = orderCommentMapper.selectList(userId);
        return list;
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
     * 新增地哪怕
     * @param orderId
     * @param orderNo
     * @param content
     * @param score
     * @param userId
     * @param imgs
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult addComment(Long orderId, String orderNo, String content, Integer score,
                          Long userId, MultipartFile[] imgs) {

        //判断是否已经存在点评
        OrderComment queryDomain = new OrderComment();
        queryDomain.setOrderId(orderId);
        queryDomain.setOrderNo(orderNo);
        queryDomain.setCreatedBy(userId);
        List<OrderComment> list = orderCommentMapper.selectOrderCommentList(queryDomain);
        if (list.size() > 0) {
            return AjaxResult.error("订单已点评~");
        }

        Date now = DateUtils.getNowDate();
        //0、上传图片
        List<String> filePath = new ArrayList<>();
        if (imgs.length > 0) {
            for (MultipartFile multipartFile : imgs) {
                System.out.println(multipartFile.getOriginalFilename());
                try {
                    String originalFilename = multipartFile.getOriginalFilename();
                    File file = new File("D:\\spring_annation\\file\\" + originalFilename);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    multipartFile.transferTo(file);
                    filePath.add(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //1、创建点评对象
        OrderComment orderComment = new OrderComment();
        Long orderCommentId = SnowflakeUtils.nextId();
        orderComment.setId(orderCommentId);
        orderComment.setContent(content);
        orderComment.setCreatedBy(userId);
        orderComment.setCommentTime(now);
        orderComment.setCreatedTime(now);
        orderComment.setOrderId(orderId);
        orderComment.setOrderNo(orderNo);
        orderComment.setScore(score);

        orderCommentMapper.insertOrderComment(orderComment);

        //2、创建详情对象
        if (filePath.size() > 0) {
            for (String path:
                 filePath) {
                CommentDetail commentDetail = new CommentDetail();
                commentDetail.setId(SnowflakeUtils.nextId());
                commentDetail.setCommentId(orderCommentId);
                commentDetail.setMediaType(1);//图片
                commentDetail.setUrl(path);
                commentDetail.setCreatedBy(userId);
                commentDetail.setCreatedTime(now);
                commentDetailMapper.insertCommentDetail(commentDetail);
            }
        }

        return AjaxResult.success("点评成功~");
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
