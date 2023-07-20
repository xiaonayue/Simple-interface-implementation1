package com.joypay.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.joypay.common.responResult;
import com.joypay.enuml.ResultCodeEnum;
import com.joypay.handler.InsufficientBalanceException;
import com.joypay.model.OrderInfo;
import com.joypay.model.User;
import com.joypay.model.dto.Orderdto;
import com.joypay.service.WalletServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dcj
 * @Date 2023/7/18 22:01
 * 演示:
 */
@Controller
@ResponseBody
@RequestMapping("/test")
public class testcontroller {
    @Autowired
    WalletServiceImp walletServiceImp;

    //查询余额
    @SaIgnore
    @GetMapping("/check")
    public User checkBalance(@RequestParam String username){

        return walletServiceImp.checkBalance(username).get(0);
    }

    //用户消费
    @SaIgnore
    @PostMapping("/consume")
    public responResult Consume(@RequestParam Long userId,
                                @RequestParam BigDecimal amount,
                                @RequestParam Orderdto orderdto){
        try {
            walletServiceImp.userConsume(userId, amount,orderdto);
            return responResult.info(ResultCodeEnum.SUCCESS,"用户消费成功");
        } catch (InsufficientBalanceException e) {
            return responResult.info(ResultCodeEnum.FAILURE,e);
        } catch (Exception e) {
            return responResult.info(ResultCodeEnum.FAILURE,"未知错误");
        }
    }

    //用户退款
    @SaIgnore
    @PostMapping("/refund")
    public responResult userRefund(@RequestParam String username,
                                   @RequestParam BigDecimal amount,
                                   @RequestParam Orderdto orderdto){
        try {
            walletServiceImp.userRefund(username, amount,orderdto);
            return responResult.info(ResultCodeEnum.SUCCESS,"用户退款成功");
        } catch (Exception e) {
            return responResult.info(ResultCodeEnum.FAILURE,"用户退款失败");
        }
    }

    //查询用户钱包金额变动明细
    @SaIgnore
    @GetMapping("/balancedetail")
    public List<OrderInfo> showUserbalancedetail(){
        return walletServiceImp.showchangeDetail();
    }

}
