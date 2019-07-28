package xyz.weechang.paddling.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;
import xyz.weechang.paddling.admin.config.PaddlingAdminProperties;
import xyz.weechang.paddling.admin.security.jwt.JwtAuthenticationProvider;
import xyz.weechang.paddling.admin.security.jwt.JwtAuthenticationRefreshFilter;
import xyz.weechang.paddling.admin.security.jwt.JwtAuthenticationSuccessHandler;
import xyz.weechang.paddling.admin.security.jwt.JwtAuthenticationTokenFilter;
import xyz.weechang.paddling.admin.security.paddling.*;
import xyz.weechang.paddling.admin.service.IPaddlingSecurityService;

import java.util.Arrays;
import java.util.List;

/**
 * 配置
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:17
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PaddlingPointAuthenticationEntryPoint paddlingPointAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private PaddlingAuthenticationFailureHandler paddlingAuthenticationFailureHandler;
    @Autowired
    private PaddlingLogoutSuccessHandler paddlingLogoutSuccessHandler;
    @Autowired
    private PaddlingAccessDeniedHandler paddlingAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private PaddlingAdminProperties paddlingAdminProperties;

    @Autowired
    private PaddlingUserDetailsService userDetailsService;
    @Autowired
    protected IPaddlingSecurityService paddlingSecurityService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private JwtAuthenticationRefreshFilter jwtAuthenticationRefreshFilter;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    /**
     * 在这里配置哪些页面不需要认证
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> noAuthPath = paddlingAdminProperties.getNoAuthPaths();
        web.ignoring().antMatchers(noAuthPath.toArray(new String[0]));
    }

    /**
     * 定义安全策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                /***未登录*/
                .httpBasic().authenticationEntryPoint(paddlingPointAuthenticationEntryPoint)

                .and()
                .authorizeRequests()

                /***RBAC权限*/
                .anyRequest()
                .access("@paddlingAuthorityService.hasPermission(request,authentication)")

                /***跨域*/
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", PaddlingAdminProperties.authKey))))
                .and()
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)

//                .and()
                .formLogin()
                /***登录成功*/
                .successHandler(jwtAuthenticationSuccessHandler)
                /***登录失败*/
                .failureHandler(paddlingAuthenticationFailureHandler)
                .permitAll()
                .and()
                .cors()

                .and()
                .logout()
                /***注销成功*/
                .logoutSuccessHandler(paddlingLogoutSuccessHandler)
                .permitAll();

        /***记住我*/
        http.rememberMe().rememberMeParameter("rememberMe").userDetailsService(userDetailsService).tokenValiditySeconds(300);

        /***无权限*/
        http.exceptionHandling().accessDeniedHandler(paddlingAccessDeniedHandler);

        /***JWT*/
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationRefreshFilter, UsernamePasswordAuthenticationFilter.class);

        /***跨域*/
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
//                = http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }
}
