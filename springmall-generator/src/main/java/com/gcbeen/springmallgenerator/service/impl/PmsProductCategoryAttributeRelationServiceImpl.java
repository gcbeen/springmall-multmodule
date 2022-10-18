package com.gcbeen.springmallgenerator.service.impl;

import com.gcbeen.springmallgenerator.entity.PmsProductCategoryAttributeRelation;
import com.gcbeen.springmallgenerator.mapper.PmsProductCategoryAttributeRelationMapper;
import com.gcbeen.springmallgenerator.service.IPmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements IPmsProductCategoryAttributeRelationService {

}
