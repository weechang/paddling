package xyz.weechang.paddling.admin.security.paddling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.weechang.paddling.admin.service.IPaddlingSecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限验证
 *
 * @author zhangwei
 * date 2019/1/27
 * time 16:39
 */
@Slf4j
@Component
public class PaddlingAuthorityService {

    @Autowired
    private IPaddlingSecurityService paddlingSecurityService;

    public boolean hasPermission(HttpServletRequest req, Authentication authentication) {
        boolean hasPermission = false;
        String username = (String) authentication.getPrincipal();
        if (username != null){
            // 公共资源
            Set<String> noAuthUrls = new HashSet<>();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : noAuthUrls) {
                if (antPathMatcher.match(url, req.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            // 登录授权资源
            if (!hasPermission){
                hasPermission = paddlingSecurityService.isUrlPermissionByName(username, req.getRequestURI());
            }
        }
        return hasPermission;
    }
}
