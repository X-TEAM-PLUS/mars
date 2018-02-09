package org.xteam.plus.mars.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: 服务提供者应用启动类
 */
@MapperScan("org.xteam.plus.mars.dao")
@SpringBootApplication(scanBasePackages = {"org.xteam.plus.mars"})
//启注解事务管理
@EnableTransactionManagement
@EnableDiscoveryClient
@Configuration
public class Service {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
    public static void main(String[] args) {
        // 程序启动入口
        SpringApplication.run(Service.class, args);
    }
}
