package com.gcbeen.springmallsearch.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gcbeen.springmallsearch.dao.IEsProductDao;
import com.gcbeen.springmallsearch.domain.EsProduct;
import com.gcbeen.springmallsearch.repository.IEsProductRepository;
import com.gcbeen.springmallsearch.service.IEsProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EsProductServiceImpl implements IEsProductService {

    @Autowired
    private IEsProductDao productDao;

    @Autowired
    private IEsProductRepository productRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esIterable.iterator();
        int result = 0;
        while(iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct product = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            product = productRepository.save(esProduct);
        }
        return product;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }

}