package com.anze.spzx.order.mapper;

import com.anze.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {

    void save(OrderInfo orderInfo);
}
