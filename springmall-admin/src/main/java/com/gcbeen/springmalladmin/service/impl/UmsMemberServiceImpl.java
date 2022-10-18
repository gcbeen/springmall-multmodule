package com.gcbeen.springmalladmin.service.impl;

import java.util.Random;

import com.gcbeen.springmallcommon.service.IRedisService;
import com.gcbeen.springmalladmin.service.IUmsMemberService;
import com.gcbeen.springmallcommon.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Service
public class UmsMemberServiceImpl implements IUmsMemberService {

    @Autowired
    private IRedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public Result<String> generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        // 验证码绑定手机号并存储到 redis
        String key = REDIS_KEY_PREFIX_AUTH_CODE+telephone;
        String value = sb.toString();
        redisService.set(key, value);
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
        return Result.success(value, "获取验证码成功");
    }

    @Override
    public Result<Boolean> verifyAuthCode(String telephone, String authCode) {
        System.out.println(" -- authCode -- " + authCode);
        if (!StringUtils.hasLength(authCode)) {
            return Result.failed("请输入验证码");
        }
        String realAuthCode = (String)redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        boolean res = authCode.equals(realAuthCode);
        if (res) {
            return Result.success(res, "验证码校验成功");
        } else {
            return Result.failed("验证码校验失败");
        }
        
    }
    
}
