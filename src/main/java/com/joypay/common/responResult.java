package com.joypay.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joypay.enuml.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author dcj
 * @Date 2023/7/18 17:30
 * 演示:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class responResult<T> {
    /**
     * 状态码
     */
    private ResultCodeEnum resultCodeEnum;
    /**
     * 响应数据
     */
    private T data;


    public responResult(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

    public responResult(T data) {
        this.data = data;
    }

    public responResult(ResultCodeEnum resultCodeEnum, T data) {
        this.resultCodeEnum = resultCodeEnum;
        this.data = data;
    }

    public static responResult info(ResultCodeEnum resultCodeEnum){
        return  new responResult(resultCodeEnum);
    }
    public static<T> responResult<T> info(ResultCodeEnum resultCodeEnum,T data){
        return  new responResult<T>(resultCodeEnum,data);
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

    public void setResultCodeEnum(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

}
