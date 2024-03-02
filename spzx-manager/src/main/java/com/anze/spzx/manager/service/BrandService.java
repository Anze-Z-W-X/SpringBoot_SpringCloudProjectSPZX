package com.anze.spzx.manager.service;

import com.anze.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分类品牌(Brand)表服务接口
 *
 * @author makejava
 * @since 2024-03-02 15:02:18
 */
public interface BrandService{

    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}

