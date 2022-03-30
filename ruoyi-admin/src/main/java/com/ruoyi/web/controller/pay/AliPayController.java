package com.ruoyi.web.controller.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Lixiangping
 * @createTime 2022年03月30日 10:19
 * @decription: 支付宝支付
 */
@RestController
public class AliPayController {

    String APP_ID = "2016092300576957";
    String CHARSET = "UTF-8";
    String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDx7Kll0fCOVCMTLCLwGsFf1+VqmGCfvPZyOU1CAn+gEnbvrppLykjKdPVdK6O3iVbD3CPcjnu6pQM5A12JRN2puj/jxjaSB62JEJsfVKrZlc+g+Y1Wxm9ER5cIcLMP+g9XwOsbu4Z95S+jK45zMvPRevKiIx5zCSMb5IGHSkdHnNwghD+8a4asejzbJdnKJKCRPse4SOrophlvXDJBe+NLzjAiyPFiHKszTEeVoN0lbB4rFPSJorz2ZkmS794yruXl4M3cLcnXjrF+jX0BBtLj5qHa3nPZ1r3RR17TG3Q+ziHQGJNvaP8naFz8omuo/mYdWwWWXRuYUSL9hjF6lNnHAgMBAAECggEBAILUy9+Go6M1GSrbKaHTtlQQu77j3Q1F8blgB8n08/QrSCrisArgwHDMw4y5dvZgzY1qVfKHE56icpW4njMIDSAm2zphDU333P9M/bb54NN+0yRpEOims7GnTaSA3jV7tCRu7ja3OUqIUTTa71SZb4YQCXtppqPrgxMohmwV3Wp5+s0jV2mV5J/TsdX7jIqjH3jpBQRJ+UXYPNMQUbpBM+cKEK4iJYBPn6xwPK61HoidbWS5uVzGXmYK9WWlD7zjLrQJB7cJWcrg0wcFEZu1jbK40kHms2gy2RoNribou5OUcCd6DA3GOeWhjnswjFMisMPipn5uE08ip4ewPwiZDsECgYEA/mYHj7xyr7SMSnvj4eGVKjPeyGWTZwrOQ9IU7x3banZUiZN4Kk0JVpMPt9GZwVb5aH3eQUprt/o2v/qP7mPzydGV17RCzW45bJNA2d/yWs7gqmwZuHaJIh00tpVeIgLQxDgJioZ0VxCZARlZK03zZURL5FXil4pg/PcD2+yx39cCgYEA83KHojnc2yU68nYLL1KN26krmgvUQnhggSchLTD7mNv2NQ4acskq2VeWbEPGwa3M0xov8FJUP/6eBozJH5/ngXBjjPkegmXRdp1vGeZVZPBiV66dDa5ZV/HLqtoGocIQb7l4oADgYGiM2R2y6fhTP8kZr/FpJ0l8PgwAEXL1V5ECgYEAkKpMmnw7a5dsP97707OIM5uTkLnSfMI0UXTh2qhL4dqwcBugNc3hBY5TXeCfYsamxbjt7or3foOXJszBlHraY2X/o84qPs/zb769HiivlNwX/lHCvpcJnJCnLTz7brPTLAIByvhyqCAUIf/cKTgGtQuFbK86T5qtVKzTUPjknbkCgYAIw0ucjEal4L4xcgxPBMK0cG9wWRKn3dtjarwn+0ZjJy/CkyJilVkkf6GbfgkOeaoQwzWMifocX5bil3i52bCkmR4MOf9eJDoY8evI2+ChBIIVs+RVVLxEjSCOotBwTCL5UozD/aQV9E11lzsUburYNe3lxmPJlTySoy3xctjf8QKBgQCU9WAXsSY+kKhvYMS2VhaqzXRhU8zHt4rvyvOOKT56Q/TOcjPTNbWmYk7W9D+Hk0Kbi9vGG20g9dn+Y05iMr9jPO9KewsxG7QezSvP893VrLJHyzQHrt32+bXnm0FSWlQt9lvZTaf8gsBGCV5NaQFRUGdfo8I+9DXV1yL9MMaU3A==";
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnnHfWXOnmk8ArzgRJPuRx3m849/ELjBeVv2C4Ax3RnykEI3FU1b72Xch7ST01rdFbV8GpcsbSQghDkoReaz9+5PRYzWnVs2+eF4mL2bQ04dvAtHheCIaFnrluhNabx0c6BLfuj74w2J8C92DI6VLG2XkteuFShH1g5iU8oj6WAZiElbDWNRrAbpEPXbcKfxIrv5PwyvkPwie6W/oSUlZowKx5tdYtJu1X90mTv1yzdvcbtToE8e9PMbZvoLV6rr3d12QgCoQ+m2BL0H629FThdiAPBb5D67gDyUEc9T8jv1ZVgALDK9xuzHFcfgjDvq/qgiBFhYralXFtvNGu5j68QIDAQAB";


    @PostMapping("/prePay")
    public AjaxResult prePay(){
        String result = "";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("B20220330001");//平台订单号
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
//        model.setGoodsDetail(null);
        request.setBizModel(model);
        request.setNotifyUrl("http://www.xxx.com/notify_url");//异步回调地址
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            result = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return AjaxResult.success(result);
    }

    @PostMapping("/alipay_notify")
    public Boolean asyncNotify(HttpServletRequest request) throws AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map< String , String > params = new HashMap< String , String >();
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String [])requestParams.get(name);
            String valueStr = "";
            for(int i = 0;i < values.length;i ++ ){
                valueStr =  (i==values.length-1)?valueStr + values [i]:valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put (name,valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean flag = AlipaySignature.rsaCheckV1 (params,ALIPAY_PUBLIC_KEY, CHARSET,"RSA2");

        return flag;
    }

}
