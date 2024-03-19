package com.anze.spzx.pay.service;

import com.anze.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> map, Integer payType);
}