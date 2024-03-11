package com.anze.spzx.product.service;

import com.anze.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {

    List<ProductSku> findProductSkuBySale();

}