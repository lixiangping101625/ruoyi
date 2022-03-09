package com.ruoyi.web.controller.sms;

import com.ruoyi.common.SmsCodeUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.ValidateCodeException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.sms.SmsCodeAuthenticationToken;
import com.ruoyi.framework.sms.SmsCodeException;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.web.vo.MobileLoginTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class SmsController {

    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTemplate redisTemplate;

    /*
     * 验证码登录
     * @param username 用户名
     * @param code 验证码
     * @return 结果
     */
    @PostMapping("/smsLogin")
    public AjaxResult loginSms(String mobile, String smsCode)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername（这块调用了UserDetailsServiceImpl的loadUserByUsername）
            authentication = authenticationManager
                    .authenticate(new SmsCodeAuthenticationToken(mobile, smsCode));
        }
        catch (Exception e)
        {
            if (e instanceof SmsCodeException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(mobile, null, Constants.LOGIN_FAIL, e.getLocalizedMessage()));
                throw new ValidateCodeException(e.getLocalizedMessage());
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(mobile, null,Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(mobile, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();//上面已经结束了 剩下交给你自己了
//        recordLoginInfo(loginUser.getUserId());
        // 生成token 这块你们根据你们的业务自己写了 tokenService.createToken使我们的业务方法
        String token = tokenService.createToken(loginUser);

        MobileLoginTokenVO tokenVO = new MobileLoginTokenVO();
        tokenVO.setToken(token);
        tokenVO.setUserId(loginUser.getUserId());
        tokenVO.setLoginTime(DateUtils.stampToTime(loginUser.getLoginTime().toString()));
        tokenVO.setExpiredTime(DateUtils.stampToTime(loginUser.getExpireTime().toString()));
        return AjaxResult.success(tokenVO);
    }

    /**
     * 发送手机验证码
     * @param mobile
     * @return
     */
    @GetMapping("/smsCode")
    public AjaxResult sendSmsCode(@RequestParam String mobile) {
        String smsCodeRedisKey = SmsCodeUtils.buildRedisSmsCodeKeyStr(mobile);
        String code = SmsCodeUtils.generateSmsCode(4);//4位数字验证码
        //验证码保存到redis，5分钟有效
        redisTemplate.opsForValue().set(smsCodeRedisKey, code, 5, TimeUnit.MINUTES);

        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        return AjaxResult.success(map);
    }


    @GetMapping("/test")
    public AjaxResult test(){
        return AjaxResult.success("手机号登录token认证通过");
    }


}
