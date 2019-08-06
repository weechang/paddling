package xyz.weechang.paddling.oss.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 16:16
 */
@ComponentScan(value = PaddlingOssConstant.componentScan)
@Configuration
public class PaddlingOssAutoConfiguration implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
