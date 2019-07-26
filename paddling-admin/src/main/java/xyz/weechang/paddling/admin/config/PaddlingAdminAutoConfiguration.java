package xyz.weechang.paddling.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 16:16
 */
@ComponentScan(value = {"xyz.weechang.paddling.admin"})
@MapperScan("xyz.weechang.paddling.admin.mapper")
@Configuration
public class PaddlingAdminAutoConfiguration {
}
