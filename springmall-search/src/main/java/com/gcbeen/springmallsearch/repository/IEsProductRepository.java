package com.gcbeen.springmallsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gcbeen.springmallsearch.domain.EsProduct;

/**
 * 商品 ES 操作类
 */
public interface IEsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    // 可以使用衍生查询
    // 在接口中直接指定查询方法名称便可查询，无需进行实现
    // 如商品表中有商品名称、标题和关键字，直接定义以下查询，就可以对这三个字段进行全文搜索。
    /**
     * 搜索查询
     * @param name 商品名称
     * @param subTitle 商品标题
     * @param keywords 商品关键字
     * @param page 分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}