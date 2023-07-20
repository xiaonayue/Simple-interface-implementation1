package com.joypay.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.babyfish.jimmer.sql.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dcj
 * @Date 2023/7/18 18:20
 * 演示:
 */
@Table(name = "orderinfo")
@Entity
public interface OrderInfo{
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();//主键id

    @Column(name = "orderNum")
    String orderNum();//订单号由自定义规则生成

    @Column(name = "orderDesc")
    String orderDesc();//订单描述

    @Column(name = "payAmount")
    BigDecimal payAmount();//付款总金额

    @Column(name = "refundAmount")
    BigDecimal refundAmount();//退款

    @Column(name = "merchantName")
    String merchantName();//商家name


    @Column(name = "paymentMethods")
    long paymentMethods();//支付方式,1余额,2第三方


    @Column(name = "payNo")
    String payNo();//微信,支付宝订单号

    @Column(name = "paymentStatus")
    long paymentStatus();//支付状态
    // 0-未支付 1-支付中 2-支付成功 3-支付失败 4-关闭订单 5-退款订单

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    LocalDateTime createTime();//订单创建时间

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    LocalDateTime updateTime();//订单修改时间

    @Version
    int vision();//乐观锁

}
