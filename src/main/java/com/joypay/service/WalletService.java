package com.joypay.service;

import com.joypay.handler.InsufficientBalanceException;
import com.joypay.model.OrderInfo;
import com.joypay.model.User;
import com.joypay.model.dto.Orderdto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dcj
 * @Date 2023/7/19 2:11
 * 演示:
 */
public interface WalletService {
    //查询用户钱包余额
    List<User> checkBalance(String username);

    //用户消费
    void userConsume(Long userId, BigDecimal amount, Orderdto orderdto) throws InsufficientBalanceException;

    //用户退款
    void userRefund(String username, BigDecimal newbalance,Orderdto orderdto);

    //查询用户钱包金额变动明细,本质是查询订单
    List<OrderInfo> showchangeDetail();
    //<OrderInfo.updateTime,OrderInfo.payAmount>


    int updateBalancebyuserName(String username,BigDecimal newbalance);
    //更新钱包余额

}
