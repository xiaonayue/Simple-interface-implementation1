package com.joypay.handler;

/**
 * @author dcj
 * @Date 2023/7/20 5:48
 * 演示:钱包异常
 */
public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
