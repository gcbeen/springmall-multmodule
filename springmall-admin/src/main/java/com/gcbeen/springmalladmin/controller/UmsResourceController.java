package com.gcbeen.springmalladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcbeen.springmalladmin.service.IUmsResourceService;
import com.gcbeen.springmallcommon.util.Result;
import com.gcbeen.springmallgenerator.entity.UmsResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台资源管理 Controller
 */
@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private IUmsResourceService resourceService;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> create(@RequestBody UmsResource umsResource) {
        boolean flag = resourceService.save(umsResource);
        
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

}
