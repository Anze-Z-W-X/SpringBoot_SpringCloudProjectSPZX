package com.anze.spzx.product.service;

import com.anze.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findOneCategory();

    List<Category> findCategoryTree();
}