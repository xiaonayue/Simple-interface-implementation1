package com.joypay.service;

import com.joypay.model.OrderInfo;
import com.joypay.model.dto.Orderdto;

import java.util.List;

/**
 * @author dcj
 * @Date 2023/7/18 14:26
 * 演示:
 */
public interface OrderService {
    //分页查询所有订单
    List<OrderInfo> selectOrdersByPage();

    //根据用户token创建订单
    //todo
    int createOrder(Orderdto orderdto);



}
