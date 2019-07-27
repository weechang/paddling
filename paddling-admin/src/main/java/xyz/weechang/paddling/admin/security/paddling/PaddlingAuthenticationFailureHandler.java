package xyz.weechang.paddling.admin.security.paddling;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.core.model.dto.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录失败
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:52
 */
@Slf4j
@Component
public class PaddlingAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        R r = R.error(PaddlingAdminError.LOGIN_ERROR);
        PrintWriter writer = null;
        try {
            writer = res.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal login failure error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
