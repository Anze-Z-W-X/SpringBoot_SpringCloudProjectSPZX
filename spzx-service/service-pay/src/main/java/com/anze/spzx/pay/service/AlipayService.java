package com.anze.spzx.pay.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface AlipayService {
    String submitAlipay(String orderNo);

    String alipayNotify(Map<String, String> paramMap, HttpServletRequest request);
}
