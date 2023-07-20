package com.joypay.enuml;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author dcj
 * @Date 2023/7/18 17:31
 * 演示:
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)//jackson序列化枚举
public enum ResultCodeEnum {
/**
 * 响应码
 */
     //成功
     SUCCESS(2000,"SUCCESS"),

    //失败
    FAILURE(4000,"FAILURE"),

    /**
     * 角色权限
     */
    //普通用户
    COMMON(1,"common"),
    //管理员
    ADMIN(2,"admin"),

    /**
     * 登录
     */
    //登陆成功
    LOGIN_SUCCESS(4001,"登陆成功"),
    //请求未授权
    UN_LOGIN(4002,"请求未授权"),

    //未通过认证
    USER_UNAUTHORIZED(4003,"用户名或密码不正确"),
    //登录失败
    FAIL_LOGIN (4005,"登录失败"),
    //
    LOGOUT (4009,"用户下线"),
    //用户不存在
    USER_NOT_EXIST(4004,"用户不存在");

    /**
     * 异常
     */
    //状态码封装
    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private void setCode(Integer code) {
        this.code = code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
