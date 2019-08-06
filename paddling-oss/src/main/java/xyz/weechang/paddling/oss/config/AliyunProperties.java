package xyz.weechang.paddling.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS存储对象
 *
 * @author zhangwei
 * date 2019/2/13
 * time 16:01
 */
@Data
@Component
@ConfigurationProperties(PaddlingOssConstant.aliyunPreKey)
public class AliyunProperties {

    /*** Region */
    private String endpoint;

    /*** accessKeyId  */
    private String accessKeyId;

    /*** accessKeySecret */
    private String accessKeySecret;

    /*** 存储空间名称 */
    private String bucketName;

    /*** 前缀 */
    private String prefix;
}
