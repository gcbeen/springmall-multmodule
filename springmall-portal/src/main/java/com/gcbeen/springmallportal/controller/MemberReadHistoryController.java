package com.gcbeen.springmallportal.controller;

import java.util.List;

import com.gcbeen.springmallcommon.util.Result;
import com.gcbeen.springmallportal.domain.MemberReadHistory;
import com.gcbeen.springmallportal.service.IMemberReadHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 用户浏览商品记录管理 Controller
 */
@RestController
@Api(tags = "MemberReadHistoryController", description = "用户浏览商品记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private IMemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count < 1) {
            return Result.failed();
        }
        return Result.success(count);
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count < 1) {
            return Result.failed();
        }
        return Result.success(count);
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<MemberReadHistory>> list(Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return Result.success(memberReadHistoryList);
    }

}
