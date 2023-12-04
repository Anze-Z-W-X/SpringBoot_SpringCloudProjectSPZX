package com.anze.spzx.common.exception;

import com.anze.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class AnzeException extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public AnzeException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
