package com.joypay.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.babyfish.jimmer.sql.meta.UserIdGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dcj
 * @Date 2023/7/18 18:42
 * 演示:自定义OrderNum生成规则
 */
public class OrderNumGenerator  {
    private final String dbSigh = "f";
    private String OrderNum;



    public void setOrderNum() {
        OrderNum = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"))+
                RandomStringUtils.randomNumeric(6)+
                dbSigh;
    }

    public String getOrderNum() {
        return OrderNum;
    }
    //    public Object generate(Class<?> aClass) {
//        //LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")
//        //获取当前时间格式化为yyMMdd,
//        //例:221019
//
//        //RandomStringUtils.randomNumeric(6)
//        //随机生成6位数
//
//        //数据库唯一标识 f
//
//        //组合到一起
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"))+
//                RandomStringUtils.randomNumeric(6)+
//                dbSigh;
//    }
}
