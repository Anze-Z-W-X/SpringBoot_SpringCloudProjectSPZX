package com.anze.spzx.manager.service;

import com.anze.spzx.model.dto.product.CategoryBrandDto;
import com.anze.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}