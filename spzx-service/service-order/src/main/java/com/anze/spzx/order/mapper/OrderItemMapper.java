package com.anze.spzx.order.mapper;

import com.anze.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    void save(OrderItem orderItem);
}
