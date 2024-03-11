package com.anze.spzx.product.service.impl;

import com.anze.spzx.model.entity.product.ProductSku;
import com.anze.spzx.product.mapper.ProductSkuMapper;
import com.anze.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        return productSkuMapper.findProductSkuBySale();
    }
}