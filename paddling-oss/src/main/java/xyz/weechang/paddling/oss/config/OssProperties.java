package xyz.weechang.paddling.oss.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * OSS config 文件
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:23
 */
@Configuration
@Data
public class OssProperties {

    /*** 本地存储Key */
    public static final String localPreKey = "paddling.oss.local";

    /*** 阿里云Key */
    public static final String aliyunPreKey = "paddling.oss.aliyun";

    /*** 七牛云Key */
    public static final String qiniuPreKey = "paddling.oss.qiniu";

    /*** 腾讯云Key */
    public static final String tencentPreKey = "paddling.oss.tencent";
}
