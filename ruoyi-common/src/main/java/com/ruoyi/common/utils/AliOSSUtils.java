package com.ruoyi.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSSymlink;
import com.ruoyi.common.config.AliyunOSSConfig;
import com.ruoyi.common.enums.OSSFileEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @author 李向平
 * @descition
 * @date 2022.0.20 12:08
 */
@Component
public class AliOSSUtils {

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    /**
     * 阿里云OSS上传单张图片
     * @param file
     * @param fileEnum 自定义OSS文件上传路径
     * @throws FileNotFoundException
     * return 图片地址
     */
    public String simpleUpload(File file,OSSFileEnum fileEnum) throws FileNotFoundException {
        String endpoint = aliyunOSSConfig.getEndpint();
        String accessKeyId = aliyunOSSConfig.getAccesskeyid();
        String accessKeySecret = aliyunOSSConfig.getAccesskeysecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //创建一个date格式化对象
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(DateUtils.getNowDate());
        String[] split = file.getName().split("\\.");
        StringBuilder key = new StringBuilder();
        switch (fileEnum){
            case USER_AVATAR:
                key.append(OSSFileEnum.USER_BASE.getDirect())
                        .append("/")
                        .append(OSSFileEnum.USER_AVATAR.getDirect())
                        .append("/")
                        .append(dateStr)
                        .append(".")
                        .append(split[1]);
                break;
            case USER_COMMENT:
                key.append(OSSFileEnum.USER_BASE.getDirect())
                        .append("/")
                        .append(OSSFileEnum.USER_COMMENT.getDirect())
                        .append("/")
                        .append(dateStr)
                        .append(".")
                        .append(split[1]);
                break;
        }
        //上传
        InputStream inputStream = new FileInputStream(file.getPath());
        ossClient.putObject(aliyunOSSConfig.getBucket(), key.toString(), inputStream);

        System.out.println("软链接地址："+getSoftLink(ossClient));
        // 关闭OSSClient。
        ossClient.shutdown();

        return key.toString();
    }


    private String getSoftLink(OSS ossClient){

        try {
            // 获取软链接以及软链接指向的目标文件名称。
            OSSSymlink symbolicLink = ossClient.getSymlink(aliyunOSSConfig.getBucket(), "test");
            System.out.println(symbolicLink.getSymlink());
            System.out.println(symbolicLink.getTarget());
            System.out.println(symbolicLink.getRequestId());
            return symbolicLink.getSymlink();
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

}