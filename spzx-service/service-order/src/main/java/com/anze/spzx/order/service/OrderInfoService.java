package com.anze.spzx.order.service;

import com.anze.spzx.model.dto.order.OrderInfoDto;
import com.anze.spzx.model.vo.h5.TradeVo;

public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);
}
