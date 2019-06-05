package com.ty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wangfei
 * @date 2019/6/5 11:53
 */
@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> redisSerializationContextBuilder = RedisSerializationContext.newSerializationContext();
        // key序列化，添加如下序列化配置解决key乱码问题以及令keys()方法生效
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisSerializationContextBuilder.key(stringRedisSerializer);
        redisSerializationContextBuilder.hashKey(stringRedisSerializer);
        // 值序列化
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisSerializationContextBuilder.value(genericJackson2JsonRedisSerializer);
        redisSerializationContextBuilder.hashValue(genericJackson2JsonRedisSerializer);
        ReactiveRedisTemplate reactiveRedisTemplate = new ReactiveRedisTemplate(connectionFactory, redisSerializationContextBuilder.build());
        return reactiveRedisTemplate;
    }
}
