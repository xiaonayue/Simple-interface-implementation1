package com.joypay.config;

import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.babyfish.jimmer.jackson.ImmutableModule;
import org.springframework.context.annotation.Bean;;
import org.springframework.stereotype.Component;



/**
 * @author dcj
 * @Date 2023/7/18 17:26
 * 演示:
 */
@Component
public class JacksonConfig {
    // This module is important,
    // it tells spring how to serialize/deserialize jimmer objects.
    @Bean
    public ImmutableModule immutableModule() {
        return new ImmutableModule();
    }

    @Bean
    public ObjectMapper getimmutebaleObjectMapper(){
        SimpleModule simpleModule = new SimpleModule();
        // 添加将long转成String的组件
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
        // 将时间反序列化组件添加到ObjectMapper中
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ImmutableModule());
        // 禁止将时间类型数据转换成时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper; }


}
