package com.wjx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wjx.database.mapper")
public class MybatisPlusConfig {
}
