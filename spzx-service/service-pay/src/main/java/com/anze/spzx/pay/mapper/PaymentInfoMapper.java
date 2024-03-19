package com.anze.spzx.pay.mapper;

import com.anze.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentInfoMapper {
    void save(PaymentInfo paymentInfo);
    PaymentInfo getByOrderNo(String orderNo);

    void updateById(PaymentInfo paymentInfo);
}
