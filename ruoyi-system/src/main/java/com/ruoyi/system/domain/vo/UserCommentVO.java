package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.system.domain.dto.OrderBaseDTO;
import com.ruoyi.system.domain.dto.PZOrderDTO;
import com.ruoyi.system.domain.dto.ZZOrderDTO;
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
    /**服务分类id*/
    private Long categoryId;
    /** 订单编码 */
    private String orderNo;
    /** 订单编码 */
    @JsonIgnore
    private String snapData;
    private PZOrderDTO pzOrderDTO;
    private ZZOrderDTO zzOrderDTO;
    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date commentTime;
    /** 评价内容 */
    private String content;
    /** 评分 */
    private Integer score;

//    private String serviceIcon;//服务图标
    private List<Media> medias;//图片列表

}

@Data
class Media implements Serializable{
    private Integer mediaType;//媒体类型
    private String url;
}