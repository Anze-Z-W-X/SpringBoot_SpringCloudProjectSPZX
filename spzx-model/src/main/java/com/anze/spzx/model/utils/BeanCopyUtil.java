package com.anze.spzx.model.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {
    public static <T> T copyBean(Object source,Class<T> clazz){
        T res = null;
        try {
            res = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source,res);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return res;
    }

    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream().map(o->copyBean(o,clazz)).collect(Collectors.toList());
    }
}
