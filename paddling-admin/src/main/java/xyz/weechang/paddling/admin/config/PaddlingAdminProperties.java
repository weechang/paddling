package xyz.weechang.paddling.admin.config;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Security 配置文件
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:59
 */
@Data
@Component
@ConfigurationProperties(prefix = PaddlingAdminProperties.PREFIX)
public class PaddlingAdminProperties {

    /***
     * 系统配置敞亮
     */
    /***配置前缀*/
    public static final String PREFIX = "paddling.admin";

    /***授权key*/
    public static final String authKey = "Authorization";

    /***授权Bearer key*/
    public static final String authBearKey = "Bearer ";

    /***不需要授权的路径*/
    private List<String> noAuthPaths;

    /***token过期时间--单位秒*/
    private int tokenExpiredTime;

    @PostConstruct
    public void init(){
        noAuthPaths = CollectionUtil.isEmpty(noAuthPaths) ? new ArrayList<>() : noAuthPaths;
    }

    public int getTokenExpiredTime(){
        return tokenExpiredTime <= 3600 ? 3600 : tokenExpiredTime;
    }

}
