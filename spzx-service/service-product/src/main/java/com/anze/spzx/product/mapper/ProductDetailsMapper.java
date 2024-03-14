package com.anze.spzx.product.mapper;

import com.anze.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {

    ProductDetails getByProductId(Long productId);

}