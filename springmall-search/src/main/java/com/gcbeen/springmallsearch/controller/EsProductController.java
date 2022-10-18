package com.gcbeen.springmallsearch.controller;

import java.util.List;

import com.gcbeen.springmallcommon.util.Result;

import com.gcbeen.springmallsearch.domain.EsProduct;
import com.gcbeen.springmallsearch.service.IEsProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private IEsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> importAllList() {
        Integer count = esProductService.importAll();
        return Result.success(count);
    }

    @ApiOperation(value = "根据 id 删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据 id 批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return Result.success(null);
    }

    @ApiOperation(value = "根据 id 创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct == null) {
            return Result.failed();
        }
        return Result.success(esProduct);
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.POST)
    @ResponseBody
    public Result<Page<EsProduct> > search(@RequestParam(required = false) String keyword,
    @RequestParam(required = false, defaultValue = "0") Integer pageNum, 
    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return Result.success(esProductPage);
    }

}
