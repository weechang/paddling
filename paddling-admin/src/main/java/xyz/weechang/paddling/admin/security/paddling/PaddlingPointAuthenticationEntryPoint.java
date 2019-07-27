package xyz.weechang.paddling.admin.security.paddling;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.core.model.dto.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义未登录
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:51
 */
@Slf4j
@Component
public class PaddlingPointAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        R r = R.error(PaddlingAdminError.USER_NOT_LOGIN);
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
