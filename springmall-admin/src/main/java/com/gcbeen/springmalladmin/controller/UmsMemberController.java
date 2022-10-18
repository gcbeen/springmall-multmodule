package com.gcbeen.springmalladmin.controller;

import com.gcbeen.springmalladmin.service.IUmsMemberService;
import com.gcbeen.springmallcommon.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// 会员登录注册管理 Controller
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private IUmsMemberService memberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value="/getAuthCode", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> updatePassword(@RequestParam String telephone, @RequestParam String authCode){
        System.out.println(" -- authCode -- " + authCode);
        return memberService.verifyAuthCode(telephone, authCode);

    }
}
