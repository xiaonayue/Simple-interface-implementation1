package com.joypay.model;

import org.babyfish.jimmer.sql.*;

import java.math.BigDecimal;

/**
 * @author dcj
 * @Date 2023/7/18 18:15
 * 演示:
 */
@Table(name = "user")
@Entity
public interface User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();//主键id

    @Key
    @Column(name = "user_name")
    String userName();//用户名
    @Key
    String phone();//用户电话
    @Key
    @Column(name = "password")
    String password();//用户密码
    String salt();//盐
    long status();//用户状态
    long role();//用户角色
    @Column(name = "user_token")
    String userToken();//用户token
    @Column(name ="balance")
    BigDecimal balance();//钱包余额
}
