package com.anze.spzx.manager.service;

import com.anze.spzx.model.dto.order.OrderStatisticsDto;
import com.anze.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
