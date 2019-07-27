package xyz.weechang.paddling.admin.security.paddling;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.core.model.dto.R;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登出成功
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:54
 */
@Slf4j
@Component
public class PaddlingLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        R r = R.ok();
        PrintWriter writer = null;
        try {
            PaddlingSecurityUtil.logout();
            writer = res.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal logout success error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
