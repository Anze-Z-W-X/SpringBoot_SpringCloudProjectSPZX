package com.anze.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.anze.spzx.common.exception.AnzeException;
import com.anze.spzx.manager.listener.ExcelListener;
import com.anze.spzx.manager.mapper.CategoryMapper;
import com.anze.spzx.manager.service.CategoryService;
import com.anze.spzx.model.entity.product.Category;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.anze.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findCategoryList(Long id) {
        //1.根据id条件值进行查询，返回List集合
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(id);
        //2.便利返回List集合,判断每个分类是否有下一层分类,如果有设置hasChildren=true
        if(!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                int count = categoryMapper.selectCountByParentId(category.getId());
                category.setHasChildren(count > 0);
            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try{
            //1.设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            //设置响应头信息,文件以下载形式
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            //2.调用mappr查询所有分类，返回List集合
            List<Category> categoryList = categoryMapper.findAll();
            //List<Category>-->List<CategoryExcelVo>
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            categoryList.forEach(category -> {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                //把category值获取出来，兵设置到categoryExcelVo中
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            });
            //3.调用EasyExcel的write完成写操作
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);
        }catch (Exception e){
            e.printStackTrace();
            throw new AnzeException(ResultCodeEnum.ACCOUNT_STOP);
        }

    }

    @Override
    public void importData(MultipartFile file) {
        //监听器
        ExcelListener excelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener)
                    .sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
            throw new AnzeException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
