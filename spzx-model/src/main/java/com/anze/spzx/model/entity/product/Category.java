package com.anze.spzx.model.entity.product;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private String name;
    private String imageUrl;
    private Long parentId;
    private Integer status;
    private Integer orderNum;
    private Boolean hasChildren;
    private List<Category> children;
}
