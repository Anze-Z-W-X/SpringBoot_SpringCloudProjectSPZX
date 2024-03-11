package com.anze.spzx.model.vo.h5;

import com.anze.spzx.model.entity.product.Category;
import com.anze.spzx.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {

    private List<Category> categoryList ;       // 一级分类的类别数据
    private List<ProductSku> productSkuList ;   // 畅销商品列表数据

}