package com.anze.spzx.model.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {


    SUCCESS(200 , "操作成功") ,
    LOGIN_ERROR(401 , "用户名或者密码错误"),
    VALIDATECODE_ERROR(402 , "验证码错误") ,
    LOGIN_AUTH(408 , "用户未登录"),
    USER_NAME_IS_EXISTS(409 , "用户名已经存在"),
    SYSTEM_ERROR(420 , "您的网络有问题请稍后重试"),
    NODE_ERROR( 417, "该节点下有子节点，不可以删除"),
    DATA_ERROR(404, "数据异常"),
    ACCOUNT_STOP( 416, "账号已停用"),
    STOCK_LESS( 419, "库存不足");

    private final Integer code ;      // 业务状态码
    private final String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
