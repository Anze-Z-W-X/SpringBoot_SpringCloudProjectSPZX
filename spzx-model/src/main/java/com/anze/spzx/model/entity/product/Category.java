package com.anze.spzx.model.entity.product;

import com.anze.spzx.model.entity.base.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class Category extends BaseEntity {
    private String name;
    private String imageUrl;
    private Long parentId;
    private Integer status;
    private Integer orderNum;
    private Boolean hasChildren;
    private List<Category> children;
}
