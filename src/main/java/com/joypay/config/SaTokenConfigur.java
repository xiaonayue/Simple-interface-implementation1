package com.joypay.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dcj
 * @Date 2023/7/18 19:15
 * 演示:
 */
@Configuration
public class SaTokenConfigur implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定一条 match 规则
            SaRouter
                    //.match("/user/**","/admin/**","/checkstand/**")    //拦截的path列表，可以写多个
                    .notMatch("/main/**","/sell/**","/test/**","/polling/**","/actuator/**","/user/**","/admin/**","/checkstand/**")        //开放的path列表
                    .check(r -> StpUtil.checkLogin());   // 要执行的校验动作，可以写完整的 lambda 表达式

            // 根据路由划分模块，不同模块不同鉴权
            //SaRouter.match("/user/**", r -> StpUtil.getRoleList().get(0).equals("common"));
            //SaRouter.match("/admin/**", r -> StpUtil.getRoleList().get(0).equals("admin"));
        })).addPathPatterns("/**");
    }
}
