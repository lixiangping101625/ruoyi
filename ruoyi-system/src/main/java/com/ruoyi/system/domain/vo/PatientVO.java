package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年03月13日 00:24
 * @decription: 就诊人员VO
 */
@Data
public class PatientVO implements Serializable {

    /** 主键 */
    private Long id;
    /** 姓名 */
    private String name;
    /** 性别：0-女1-男2-未知 */
    private Long gender;
    /** 联系方式 */
    private String contact;
    /** 就诊人身份证号码 */
    private String cardNum;
    /** 客户端用户id */
    private Long userId;
    private Integer relation;
    private String relationStr;

}
