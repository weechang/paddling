package xyz.weechang.paddling.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.weechang.paddling.core.exception.PaddlingExceptionHandler;

/**
 * spring mvc 异常映射
 *
 * @author weechang
 * @date 2017年11月18日23:44:36
 */
@Configuration
public class PaddlingExceptionAutoConfig {

    @Bean
    @ConditionalOnMissingBean({PaddlingExceptionHandler.class})
    public PaddlingExceptionHandler defaultExceptionAdvice() {
        return new PaddlingExceptionHandler();
    }
}
