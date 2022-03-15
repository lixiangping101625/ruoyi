package com.ruoyi.web.controller.message;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.message.PushChannel;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.req.message.PushMessage;
import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;
import com.getui.push.v2.sdk.dto.req.message.android.ThirdNotification;
import com.getui.push.v2.sdk.dto.req.message.android.Ups;
import com.getui.push.v2.sdk.dto.req.message.ios.Alert;
import com.getui.push.v2.sdk.dto.req.message.ios.Aps;
import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public class TestPushApi {

    //pushApi的创建见上述使用示例：创建API
//    public PushApi pushApi;

    @GetMapping("/getui")
    public static void main(String[] args) {
        System.setProperty("http.maxConnections", "200");
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        //填写应用配置
        apiConfiguration.setAppId("oJy4yqY4Gq59I6cdp0Nic6");
        apiConfiguration.setAppKey("bcRx7JNDtd70GTNnN3OqE5");
        apiConfiguration.setMasterSecret("4lXkAQEfW87nu7jKS8Clu9");
        // 接口调用前缀，请查看文档: 接口调用规范 -> 接口前缀, 可不填写appId
        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        // 实例化ApiHelper对象，用于创建接口对象
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        // 创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
        PushApi pushApi = apiHelper.creatApi(PushApi.class);

        //根据cid进行单推
        PushDTO<Audience> pushDTO = new PushDTO<Audience>();
        // 设置推送参数
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        /**** 设置个推通道参数 *****/
        PushMessage pushMessage = new PushMessage();
        pushDTO.setPushMessage(pushMessage);
//        GTNotification notification = new GTNotification();
//        pushMessage.setNotification(notification);

        pushMessage.setTransmission("愿伴提醒：您的订单支付成功~ 2022-03-15 21:05");


//        notification.setTitle("个title");
//        notification.setBody("个body");
//        notification.setClickType("url");
//        notification.setUrl("https://www.getui.com");
        /**** 设置个推通道参数，更多参数请查看文档或对象源码 *****/

        /**** 设置厂商相关参数 ****/
        PushChannel pushChannel = new PushChannel();
        pushDTO.setPushChannel(pushChannel);
        /*配置安卓厂商参数*/
        AndroidDTO androidDTO = new AndroidDTO();
        pushChannel.setAndroid(androidDTO);
        Ups ups = new Ups();
        androidDTO.setUps(ups);
        ThirdNotification thirdNotification = new ThirdNotification();
        ups.setNotification(thirdNotification);
        thirdNotification.setTitle("厂商title");
        thirdNotification.setBody("厂商body");
        thirdNotification.setClickType("url");
        thirdNotification.setUrl("https://www.getui.com");
        // 两条消息的notify_id相同，新的消息会覆盖老的消息，取值范围：0-2147483647
        // thirdNotification.setNotifyId("11177");
        /*配置安卓厂商参数结束，更多参数请查看文档或对象源码*/

        /*设置ios厂商参数*/
        IosDTO iosDTO = new IosDTO();
        pushChannel.setIos(iosDTO);
        // 相同的collapseId会覆盖之前的消息
        iosDTO.setApnsCollapseId("xxx");
        Aps aps = new Aps();
        iosDTO.setAps(aps);
        Alert alert = new Alert();
        aps.setAlert(alert);
        alert.setTitle("ios title");
        alert.setBody("ios body");
        /*设置ios厂商参数结束，更多参数请查看文档或对象源码*/

        /*设置接收人信息*/
        Audience audience = new Audience();
        pushDTO.setAudience(audience);
        audience.addCid("81f4d37dc4bb9dbfb09a9e2d0eb9f2c2");
        /*设置接收人信息结束*/
        /**** 设置厂商相关参数，更多参数请查看文档或对象源码 ****/

        // 进行cid单推
        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByCid(pushDTO);
        if (apiResult.isSuccess()) {
            // success
            System.out.println(apiResult.getData());
        } else {
            // failed
            System.out.println("code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }
}