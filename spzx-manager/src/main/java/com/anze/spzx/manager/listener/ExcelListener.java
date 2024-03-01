package com.anze.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.anze.spzx.manager.mapper.CategoryMapper;
import com.anze.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

public class ExcelListener<T> implements ReadListener<T> {
    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //构造传递mapper，操作数据库
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper=categoryMapper;
    }
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //把每行数据的对象t放到cachedDataList集合里
        cachedDataList.add(t);
        if(cachedDataList.size()>=BATCH_COUNT){
            //调用方法批量添加到数据库里
            saveData();
            //清理list集合
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //保存数据(未达到100行的数据)
        saveData();
    }

    //保存的方法
    private void saveData(){
        categoryMapper.batchInsrt((List<CategoryExcelVo>) cachedDataList);
    }
}
