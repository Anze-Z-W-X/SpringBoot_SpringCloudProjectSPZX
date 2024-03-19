package com.anze.spzx.order.mapper;

import com.anze.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderLogMapper {
    void save(OrderLog orderLog);
}
