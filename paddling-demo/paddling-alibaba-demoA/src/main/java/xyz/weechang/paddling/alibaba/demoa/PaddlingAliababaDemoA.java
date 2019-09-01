package xyz.weechang.paddling.alibaba.demoa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2019/8/8
 * time 10:18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaddlingAliababaDemoA extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PaddlingAliababaDemoA.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PaddlingAliababaDemoA.class);
    }

    @Slf4j
    @RestController
    static class TestController {

        @GetMapping("/hello")
        public String hello(@RequestParam String name) {
            log.info("invoked name = " + name);
            return "hello " + name;
        }
    }
}
