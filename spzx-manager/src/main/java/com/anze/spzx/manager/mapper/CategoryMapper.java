package com.anze.spzx.manager.mapper;

import com.anze.spzx.model.entity.product.Category;
import com.anze.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryByParentId(Long parentId);

    int selectCountByParentId(Long id);

    List<Category> findAll();

    void batchInsrt(List<CategoryExcelVo> cachedDataList);
}
