package com.anze.spzx.manager.controller;

import com.anze.spzx.manager.service.BrandService;
import com.anze.spzx.model.entity.product.Brand;
import com.anze.spzx.model.vo.common.Result;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService ;

    //列表
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    //添加
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //修改
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //查询所有品牌
    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

}
