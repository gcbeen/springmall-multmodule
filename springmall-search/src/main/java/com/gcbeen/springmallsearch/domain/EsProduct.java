package com.gcbeen.springmallsearch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

// pms_product：商品信息表
// pms_product_attribute：商品属性参数表
// pms_product_attribute_value：存储产品参数值的表

/**
 * 搜索中的商品信息
 */
// , type = "product"
@Data
@Document(indexName = "pms", shards = 1, replicas = 0 )
public class EsProduct implements Serializable {
    @Id
    private Long id;
    @Field(type=FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type=FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type=FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(analyzer="ik_max_word", type=FieldType.Text)
    private String name;
    @Field(analyzer="ik_max_word", type=FieldType.Text)
    private String subTitle;
    @Field(analyzer="ik_max_word", type=FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type=FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;


}