package org.xteam.plus.mars.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: admin 应用启动类
 */
@EnableDiscoveryClient // Eureka Discovery Client 标识
@SpringBootApplication(scanBasePackages = {"org.xteam.plus.mars"}) // Spring Boot 应用标识
@EnableZuulProxy
@Controller
public class Admin {
    public static void main(String[] args) {
        // 程序启动入口
        SpringApplication.run(Admin.class, args);
    }

    @RequestMapping("/mars/{model}/{path}")
    public String forward(@PathVariable String model, @PathVariable String path) {
        return "/" + model + "/" + path;
    }
}
