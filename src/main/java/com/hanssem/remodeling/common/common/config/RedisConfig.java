package com.hanssem.remodeling.common.common.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@EnableRedisRepositories
@Slf4j
@RequiredArgsConstructor
public class RedisConfig extends CachingConfigurerSupport {

    private final ObjectMapper objectMapper;
    private static final String BASE_CACHE_NAME_PREFIX = "api-common:base:";

    @Bean
    @Primary
    public CacheManager baseCacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(this.getGenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues()
                .prefixCacheNameWith(BASE_CACHE_NAME_PREFIX)
                .entryTtl(Duration.ofDays(1));

        return RedisCacheManager.RedisCacheManagerBuilder.
                fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        // GenericJackson2JsonRedisSerializer() ????????? ????????? ??????, objectMapper??? ?????? ????????????.
        // ??????????????? ????????? objectMapper??? ????????? ???????????? ???????????? ?????????, objectMapper??? ???????????? GenericJackson2JsonRedisSerializer??? ????????????.

        ObjectMapper redisObjectMapper = objectMapper.copy();

        redisObjectMapper.disable(SerializationFeature.INDENT_OUTPUT);

        StdTypeResolverBuilder typer = new DefaultTypeResolverBuilder(DefaultTyping.EVERYTHING, redisObjectMapper.getPolymorphicTypeValidator());
        typer = typer.init(JsonTypeInfo.Id.CLASS, null);
        typer = typer.inclusion(JsonTypeInfo.As.PROPERTY);

        redisObjectMapper.setDefaultTyping(typer);

        log.debug("# create GenericJackson2JsonRedisSerializer from objectMapper.");
        return new GenericJackson2JsonRedisSerializer(redisObjectMapper);
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("Unable to GET from cache [{}] : {}", cache.getName(), exception.getMessage());
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("Unable to PUT from cache [{}] : {}", cache.getName(), exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("Unable to EVICT from cache [{}] : {}", cache.getName(), exception.getMessage());
                throw exception;
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("Unable to CLEAR from cache [{}] : {}", cache.getName(), exception.getMessage());
                throw exception;
            }
        };
    }
}