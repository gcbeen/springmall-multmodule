package com.gcbeen.springmalladmin.config;

import com.gcbeen.springmallcommon.config.BaseRedisConfig;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
