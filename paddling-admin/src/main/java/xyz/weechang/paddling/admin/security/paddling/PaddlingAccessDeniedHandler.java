package xyz.weechang.paddling.admin.security.paddling;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.core.model.dto.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义403响应内容
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:09
 */
@Slf4j
@Component
public class PaddlingAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        R r = R.error(PaddlingAdminError.ACCESS_FORBIDDEN);
        PrintWriter writer = null;
        try {
            writer = res.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal access denied error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
