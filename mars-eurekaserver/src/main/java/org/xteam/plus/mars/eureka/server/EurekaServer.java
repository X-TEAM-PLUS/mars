package org.xteam.plus.mars.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:Spring Boot Eureka Server 应用启动类
 */
@EnableEurekaServer // Eureka Server 标识
@EnableEurekaClient
@SpringBootApplication // Spring Boot 应用标识
public class EurekaServer {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(EurekaServer.class, args);
    }
}
