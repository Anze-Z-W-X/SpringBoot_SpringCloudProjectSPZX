package com.anze.spzx.order.service;

import com.anze.spzx.model.dto.order.OrderInfoDto;
import com.anze.spzx.model.entity.order.OrderInfo;
import com.anze.spzx.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;

public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);
}
