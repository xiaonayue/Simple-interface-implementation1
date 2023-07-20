package com.joypay.service;

import com.joypay.enuml.ResultCodeEnum;
import com.joypay.handler.InsufficientBalanceException;
import com.joypay.model.*;
import com.joypay.model.dto.Orderdto;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.babyfish.jimmer.sql.ast.query.ConfigurableRootQuery;
import org.babyfish.jimmer.sql.ast.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dcj
 * @Date 2023/7/19 2:12
 * 演示:
 */
@Service
public class WalletServiceImp implements WalletService{
    @Autowired
    JSqlClient sqlClient;
    @Autowired
    OrderInfoTable orderInfo;
    @Autowired
    UserTable userTable;
    @Autowired
    OrderServiceImpl orderServiceimp;
    @Autowired
    WalletServiceImp walletServiceImp;

    @Override
    public List<User> checkBalance(String username) {
        final UserTable usertable = UserTable.$;
        return sqlClient
                .createQuery(usertable)
                .where(usertable.userName().eq(username))
                .select(usertable.fetch(UserFetcher.$.balance()))//抓取标量字段
                .execute();
    }

    @Override
    @Transactional(rollbackFor = InsufficientBalanceException.class)//采用声明式事务
    public void userConsume(Long userId, BigDecimal amount, Orderdto orderdto) throws InsufficientBalanceException {
        //获取实体
        final User user = sqlClient.getEntities().findById(User.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        //初始化订单
        orderServiceimp.createOrder(orderdto);
        //判断支付方式
        final List<OrderInfo> paymethod = sqlClient.createQuery(orderInfo).select(
                orderInfo.fetch(OrderInfoFetcher.$.paymentMethods())).execute();
        //这里做简单处理,只进行余额支付
        if (paymethod == null) {
            throw new IllegalArgumentException("Order not found.");
        }if(paymethod.get(0).paymentMethods()==1){
            System.out.println("检查钱包余额");
        }if(user.balance().subtract(amount).doubleValue()<0){
            throw new IllegalArgumentException("余额不足");
        }if(user.balance().subtract(amount).doubleValue()>=0){
            //更新钱包余额
            walletServiceImp.updateBalancebyuserName(user.userName(),user.balance().subtract(amount));
        }//更新订单信息,这里不写了...todo



    }

    @Override
    public void userRefund(String username, BigDecimal newbalance,Orderdto orderdto) {
         walletServiceImp.updateBalancebyuserName(username,newbalance);
    }

    @Override
    public int updateBalancebyuserName(String username, BigDecimal newbalance) {
        final int affectedRowCount = sqlClient
                .getEntities()
                .saveCommand(
                        UserDraft.$.produce(book -> book
                                .setUserName(username)
                                .setBalance(newbalance))
                ).configure(it -> it.setMode(SaveMode.UPDATE_ONLY))
                .execute().getTotalAffectedRowCount();
        return affectedRowCount;
    }

    @Override
    public List<OrderInfo> showchangeDetail() {
        //经过分页查询得到的所有订单
        final List<OrderInfo> allorders = orderServiceimp.selectOrdersByPage();
        //使用stream过滤
        final List<OrderInfo> details = allorders.stream()
                .filter(orders -> orders.paymentMethods()==1)//1为余额支付
                .collect(Collectors.toList());
        return details;
    }


}
