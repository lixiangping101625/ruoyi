package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 李向平
 * @descition
 * @date 2022.0.20 12:08
 */

@ConfigurationProperties(prefix = "aliyunoss")
@Component
@Data
public class AliyunOSSConfig {
    private String endpint;
    private String accesskeyid;
    private String accesskeysecret;
    private String bucket;

}