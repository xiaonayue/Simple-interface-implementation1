package com.joypay.Bean;

import cn.hutool.core.text.AntPathMatcher;
import com.joypay.model.OrderInfoTable;
import com.joypay.model.UserTable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



/**
 * @author dcj
 * @Date 2023/7/18 18:58
 * 演示:
 */
@Component
public class Entity {
    @Bean
    public UserTable getUserTable(){ return new UserTable(); }
    @Bean
    public AntPathMatcher getAntPathMatcher(){
        return new AntPathMatcher();
    }
    @Bean
    public OrderInfoTable getOrderInfoTable(){
        return new OrderInfoTable();
    }



}
