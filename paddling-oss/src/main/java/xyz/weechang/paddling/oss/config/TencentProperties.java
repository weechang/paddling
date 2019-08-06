package xyz.weechang.paddling.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 腾讯云存储
 *
 * @author zhangwei
 * date 2019/2/13
 * time 16:02
 */
@Data
@Component
@ConfigurationProperties(PaddlingOssConstant.tencentPreKey)
public class TencentProperties {

    /*** 腾讯云路径前缀 */
    private String prefix;

    /*** 腾讯云AppId */
    private Integer appId;

    /*** 腾讯云SecretId */
    private String secretId;

    /*** 腾讯云SecretKey */
    private String secretKey;

    /*** 腾讯云BucketName */
    private String bucketName;

    /*** 腾讯云COS所属地区 */
    private String region;
}
