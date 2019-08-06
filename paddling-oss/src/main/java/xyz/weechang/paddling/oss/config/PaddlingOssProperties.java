package xyz.weechang.paddling.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OSS config 文件
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:23
 */
@Data
@Component
@ConfigurationProperties(PaddlingOssConstant.paddlingOss)
public class PaddlingOssProperties {

    private String defaultOss;
}
