package xyz.weechang.paddling.demo.admin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 17:24
 */
@SpringBootApplication
public class PaddlingAdminDemoApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PaddlingAdminDemoApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PaddlingAdminDemoApp.class);
    }
}
