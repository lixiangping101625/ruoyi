package com.ruoyi.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
* @Description: 短信发送
*/
@Component
public class AliSMS {

    private static String accessKeyId;
    private static String accessKeySecret;
    private static String signName;
    private static String templateCode;

    @Value("${sms.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        AliSMS.accessKeyId = accessKeyId;
    }
    @Value("${sms.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        AliSMS.accessKeySecret = accessKeySecret;
    }
    @Value("${sms.signName}")
    public void setSignName(String signName) {
        AliSMS.signName = signName;
    }
    @Value("${sms.templateCode}")
    public void setTemplateCode(String templateCode) {
        AliSMS.templateCode = templateCode;
    }

    public static boolean sendSms(String mobile, String codeVerify){
        String result = "";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);//接收短信的手机号码
        request.setSignName(signName);//短信签名名称
        request.setTemplateCode(templateCode);//短信模板CODE
        request.setTemplateParam("{\"code\":\"" + codeVerify + "\"}");//短信模板变量对应的实际值

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return false;
    }


}