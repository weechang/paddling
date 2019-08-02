package xyz.weechang.paddling.core.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import xyz.weechang.paddling.core.interceptor.SQLAuditInterceptor;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 18:02
 */
@ComponentScan(value = {"xyz.weechang.paddling.core"})
public class PaddlingCoreAutoConfiguration {

    /*** mybatis plus 分页插件 */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /*** mybatis SQL审计插件 */
    @Bean
    public SQLAuditInterceptor sqlAuditInterceptor() {
        SQLAuditInterceptor sqlAuditInterceptor = new SQLAuditInterceptor();
        return sqlAuditInterceptor;
    }
}
