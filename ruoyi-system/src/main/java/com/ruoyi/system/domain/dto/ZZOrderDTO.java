package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年03月17日 15:32
 * @decription: 增值类服务下单时dto
 */
@Data
public class ZZOrderDTO extends OrderBaseDTO {

    private Date expectHandleTime;//期望处理时间
    private String reportInfo;//报告信息
    private String qrCode;//就诊二维码
    private String cardNo;//就诊卡号

    private String receiveAddr;//收件地址
    private String detailAddr;//详细地址


}
