package xyz.weechang.paddling.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地文件配置
 *
 * @author zhangwei
 * date 2019/7/30
 * time 16:07
 */
@Data
@Component
@ConfigurationProperties(PaddlingOssConstant.localPreKey)
public class LoacalProperties {

    /*** 本地存储基本路径 */
    private String baseDir;
}
