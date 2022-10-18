package com.gcbeen.springmalladmin.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcbeen.springmalladmin.service.IPmsProductService;
import com.gcbeen.springmallcommon.util.Result;
import com.gcbeen.springmallgenerator.entity.PmsProduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "ProductController", description = "spring mall API 接口数据")
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private IPmsProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductController.class);

    @PreAuthorize("hasAuthority('pms:product:read')")
    @ApiOperation(value = "获取所有产品列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsProduct>> getProductList() {
        List<PmsProduct> productList = productService.list();
        return Result.success(productList, "产品数据获取成功");
    }

    @PreAuthorize("hasAuthority('pms:product:create')")
    @ApiOperation("添加产品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result<PmsProduct> createProduct(@RequestBody PmsProduct product) {
        Result<PmsProduct> result;
        boolean isSave = productService.save(product);
        if (isSave) {
            result = Result.success(product);
            LOGGER.debug("create product success:{}", product);
        } else {
            result = Result.failed("添加产品失败");
            LOGGER.debug("create product failed:{}", product);
        }
        return result;
    }

    @PreAuthorize("hasAuthority('pms:product:update')")
    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result<PmsProduct> updateProduct(@PathVariable("id") Long id, @RequestBody PmsProduct product,
            BindingResult result) {
        Result<PmsProduct> resp;
        boolean isUpdate = productService.updateById(product);
        if (isUpdate) {
            resp = Result.success(product);
            LOGGER.debug("update product success:{}", product);
        } else {
            resp = Result.failed("更新失败");
            LOGGER.debug("update product failed:{}", product);
        }
        return resp;
    }

    @PreAuthorize("hasAuthority('pms:product:delete')")
    @ApiOperation("删除指定 id 的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Result<PmsProduct> deleteProduct(@PathVariable("id") Long id) {
        Result<PmsProduct> resp;
        boolean isDelete = productService.removeById(id);
        if (isDelete) {
            resp = Result.success(null);
            LOGGER.debug("delete product success :id= {}", id);
        } else {
            resp = Result.failed("删除失败");
            LOGGER.debug("delete product failed :id= {}", id);
        }
        return resp;
    }

    @PreAuthorize("hasAuthority('pms:product:read')")
    @ApiOperation("获取指定 id 的产品")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<PmsProduct> product(@PathVariable("id") Long id) {
        PmsProduct product = productService.getById(id);
        return Result.success(product);
    }

    @PreAuthorize("hasAuthority('pms:product:read')")
    @ApiOperation("分页获取产品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<IPage<PmsProduct>> listProduct(@RequestParam(value = "brandId") @ApiParam("品牌ID") Integer brandId,
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize) {
        IPage<PmsProduct> productList = productService.pageList(pageNum, pageSize, brandId);
        return Result.success(productList);

    }

}
