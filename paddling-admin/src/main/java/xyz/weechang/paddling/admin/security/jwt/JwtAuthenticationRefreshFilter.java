package xyz.weechang.paddling.admin.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.weechang.paddling.admin.config.PaddlingAdminProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * token 刷新filter
 *
 * @author zhangwei
 * date 2019/2/18
 * time 15:50
 */
@Component
public class JwtAuthenticationRefreshFilter extends OncePerRequestFilter {

    private static final int tokenRefreshInterval = 300;  //刷新间隔5分钟

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        String authHeader = req.getHeader(PaddlingAdminProperties.authKey);
        if (authHeader != null && authHeader.startsWith(PaddlingAdminProperties.authBearKey) && !reqUri.equals("/login")) {
            final String authToken = authHeader.substring(PaddlingAdminProperties.authBearKey.length());
            try {
                DecodedJWT decodedJWT = JWT.decode(authToken);
                String username = decodedJWT.getSubject();
                if (username != null) {
                    UserDetails userDetails = jwtUserDetailsService.getUserLoginInfo(username);
                    if (userDetails != null) {
                        res.setStatus(HttpServletResponse.SC_OK);
                        res.setCharacterEncoding("UTF-8");
                        res.setContentType("application/json; charset=utf-8");
                        String token = decodedJWT.getToken();
                        boolean shouldRefresh = shouldTokenRefresh(decodedJWT.getIssuedAt());
                        if (shouldRefresh) {
                            token = jwtUserDetailsService.loginSuccess(userDetails);
                        }
                        res.setHeader(PaddlingAdminProperties.authKey, token);
                    }

                }
            } catch (Exception e) {
                logger.error("wrong token attempted:", e);
            }
        }
        chain.doFilter(req, res);
    }

    protected boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
