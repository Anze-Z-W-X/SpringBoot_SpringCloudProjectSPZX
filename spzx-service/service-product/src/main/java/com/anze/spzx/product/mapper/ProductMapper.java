package com.anze.spzx.product.mapper;

import com.anze.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    Product getById(Long id);
}