package xyz.weechang.paddling.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.weechang.paddling.admin.security.jwt.*;
import xyz.weechang.paddling.admin.security.paddling.*;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 16:16
 */
@ComponentScan(value = {"xyz.weechang.paddling.admin"})
@MapperScan("xyz.weechang.paddling.admin.mapper")
@Configuration
public class PaddlingAdminAutoConfiguration implements BeanFactoryAware {

    @Bean
    @ConditionalOnMissingBean
    public PaddlingPointAuthenticationEntryPoint paddlingPointAuthenticationEntryPoint() {
        return new PaddlingPointAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public PaddlingAuthenticationFailureHandler paddlingAuthenticationFailureHandler() {
        return new PaddlingAuthenticationFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public PaddlingLogoutSuccessHandler paddlingLogoutSuccessHandler() {
        return new PaddlingLogoutSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public PaddlingAccessDeniedHandler paddlingAccessDeniedHandler() {
        return new PaddlingAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public PaddlingAdminProperties paddlingAdminProperties() {
        return new PaddlingAdminProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationRefreshFilter jwtAuthenticationRefreshFilter() {
        return new JwtAuthenticationRefreshFilter();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
