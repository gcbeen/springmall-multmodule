package com.gcbeen.springmalladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcbeen.springmallgenerator.entity.PmsProduct;

public interface IPmsProductService extends IService<PmsProduct> {
    public IPage<PmsProduct> pageList(Integer pageNum, Integer pageSize, Integer brandId);
}
