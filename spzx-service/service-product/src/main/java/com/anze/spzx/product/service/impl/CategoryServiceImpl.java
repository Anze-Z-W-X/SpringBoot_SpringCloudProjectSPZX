package com.anze.spzx.product.service.impl;

import com.anze.spzx.model.entity.product.Category;
import com.anze.spzx.product.mapper.CategoryMapper;
import com.anze.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }
}
