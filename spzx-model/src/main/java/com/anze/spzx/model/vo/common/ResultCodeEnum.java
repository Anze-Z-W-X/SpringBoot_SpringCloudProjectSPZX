package com.anze.spzx.model.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    LOGIN_ERROR(201 , "用户名或者密码错误");

    private final Integer code ;      // 业务状态码
    private final String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
