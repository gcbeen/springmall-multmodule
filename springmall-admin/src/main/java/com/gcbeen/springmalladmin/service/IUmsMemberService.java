package com.gcbeen.springmalladmin.service;

import com.gcbeen.springmallcommon.util.Result;

/**
 * 会员管理 Service
 */
public interface IUmsMemberService {
    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    Result<String> generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码 是否匹配
     * @param telephone
     * @param authCode
     * @return
     */
    Result<Boolean> verifyAuthCode(String telephone, String authCode);
}
