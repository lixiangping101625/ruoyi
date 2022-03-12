package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceInfoVO implements Serializable
{

    private Long id;
    /** 服务名称 */
    private String serviceName;
    /** 图标 */
    private String icon;
    /** 服务类目编码 */
    private Long categoryId;

}
