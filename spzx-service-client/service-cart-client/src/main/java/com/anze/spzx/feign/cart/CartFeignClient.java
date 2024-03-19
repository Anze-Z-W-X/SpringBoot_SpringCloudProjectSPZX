package com.anze.spzx.feign.cart;

import com.anze.spzx.model.entity.h5.CartInfo;
import com.anze.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    public abstract List<CartInfo> getAllCkecked() ;

    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public abstract Result deleteChecked() ;

}