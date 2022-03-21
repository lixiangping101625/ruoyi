package com.ruoyi.framework.sms;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.framework.web.service.UserDetailsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 手机号登录认证类
 */
@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {

	//用户验证处理 实现Spring Security提供的UserDetailsService的类
	private UserDetailsServiceImpl userDetailsServiceImpl;
	//redis缓存 用你的缓存就行 这个就不给了 用来存放验证码的
    @Resource
    private RedisCache redisCache;

    final static String MOBILE_SMS_CODE = "mobile_sms_code";

    public SmsAuthenticationProvider(@Qualifier("userDetailsServiceImpl") UserDetailsServiceImpl smsUserDetailsServiceImpl, RedisCache redisCache) {
        this.userDetailsServiceImpl = smsUserDetailsServiceImpl;
        this.redisCache = redisCache;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        Object principal = authentication.getPrincipal();// 获取凭证也就是用户的手机号
        String phone = "";
        if (principal instanceof String) {
            phone = (String) principal;
        }
        
        String inputCode = (String) authentication.getCredentials(); // 获取输入的验证码
        if (StringUtils.isEmpty(inputCode)) {
            throw new SmsCodeException("请输入手机验证码");
        }
        System.out.println("验证码key：" + MOBILE_SMS_CODE.concat(":").concat(phone));
        System.out.println("用户输入验证码：" + inputCode);
        Object redisCode = redisCache.getCacheObject(MOBILE_SMS_CODE.concat(":").concat(phone));
        String cacheObject = redisCode != null? redisCode.toString() : "";
        // 1. 检验Redis手机号的验证码
        if (StringUtils.isEmpty(cacheObject)) {
            throw new SmsCodeException("验证码已经过期或尚未发送，请重新发送验证码~");
        }
        if (!inputCode.equals(cacheObject)) {
            throw new SmsCodeException("输入的验证码不正确，请重新输入~");
        }
        redisCache.deleteObject(MOBILE_SMS_CODE.concat(":").concat(phone));
        // 2. 根据手机号查询用户信息
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(phone);
//        if (userDetails == null) {
//            throw new InternalAuthenticationServiceException("手机用户不存在，请注册");
//        }
         // 3. 重新创建已认证对象,
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, inputCode, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}

