package com.gcbeen.springmalladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcbeen.springmallgenerator.entity.PmsProduct;

// @Deprecated
public interface PmsProductDao extends BaseMapper<PmsProduct> {
    /**
     *
     * @param page    分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param brandId 品牌
     * @return 分页对象
     */
    IPage<PmsProduct> pageList(Page<PmsProduct> page, Integer brandId);
}
