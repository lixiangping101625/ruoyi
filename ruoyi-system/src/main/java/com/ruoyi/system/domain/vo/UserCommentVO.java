package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年03月14日 20:48
 * @decription: 用户点评VO
 */
@Data
public class UserCommentVO implements Serializable {

    private Long orderId;
    /** 订单编码 */
    private String orderNo;
    /** 评价内容 */
    private String content;
    /** 分数 */
    private Long score;
    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date commentTime;

    private String serviceIcon;//服务图标
    private List<String> imgs;//图片列表



}
