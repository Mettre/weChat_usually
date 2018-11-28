package com.mettre.usually.exception;

import com.mettre.usually.constant.CommonConstant;
import com.mettre.usually.enum_.ResultEnum;

public class CustomerException extends RuntimeException {
    private String errorCode;

    public CustomerException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public CustomerException(String message) {
        super(message);
        this.errorCode = CommonConstant.ERROR;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}