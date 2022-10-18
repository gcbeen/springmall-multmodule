package com.gcbeen.springmallsearch.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.gcbeen.springmallsearch.domain.EsProduct;

/**
 * 搜索系统中的商品管理自定义Dao
 */

public interface IEsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}