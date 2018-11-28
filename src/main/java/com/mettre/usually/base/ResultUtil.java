package com.mettre.usually.base;

import com.mettre.usually.constant.CommonConstant;
import com.mettre.usually.enum_.ResultEnum;

/**
 * @author Exrick
 */
public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(CommonConstant.UNKNOW);
    }

    public Result<T> setData(T t) {
        this.result.setData(t);
        this.result.setCode(CommonConstant.UNKNOW);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(CommonConstant.UNKNOW);
        this.result.setData(null);
        return this.result;
    }

    public Result<T> setSuccess() {
        this.result.setSuccess(true);
        this.result.setMessage("成功");
        this.result.setCode(CommonConstant.UNKNOW);
        this.result.setData(null);
        return this.result;
    }

    public Result<T> setData(T t, String msg) {
        this.result.setData(t);
        this.result.setCode(CommonConstant.UNKNOW);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(CommonConstant.ERROR);
        return this.result;
    }

    public Result<T> setErrorResultEnum(ResultEnum resultEnum) {
        this.result.setSuccess(false);
        this.result.setMessage(resultEnum.getMsg());
        this.result.setCode(resultEnum.getCode());
        return this.result;
    }

    public Result<T> setNotLoggedInMsg() {
        this.result.setSuccess(false);
        this.result.setMessage("未登录");
        this.result.setCode("401");
        return this.result;
    }

    public Result<T> setAuthenticationFailureMsg() {
        this.result.setSuccess(false);
        this.result.setMessage("登录失效");
        this.result.setCode("401");
        return this.result;
    }

    public Result<T> setErrorMsg(String code, String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }
}
