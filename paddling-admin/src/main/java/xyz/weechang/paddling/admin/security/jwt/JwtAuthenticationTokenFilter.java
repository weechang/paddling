package xyz.weechang.paddling.admin.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.weechang.paddling.admin.config.PaddlingAdminProperties;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 授权验证
 *
 * @author zhangwei
 * date 2019/1/27
 * time 16:36
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        String reqUri = req.getRequestURI();
        String authHeader = req.getHeader(PaddlingAdminProperties.authKey);

        if (authHeader != null && authHeader.startsWith("Bearer ") && !reqUri.equals("/login")) {
            final String authToken = authHeader.substring("Bearer ".length());

            try {
                DecodedJWT decodedJWT = JWT.decode(authToken);
                String username = decodedJWT.getSubject();

                if (username != null) {
                    UserDetails userDetails = jwtUserDetailsService.getUserLoginInfo(username);

                    if (userDetails != null) {
                        PaddlingSecurityUtil.setLoginInfo(username, null);
                        JwtAuthenticationToken authentication = new JwtAuthenticationToken(userDetails, decodedJWT, null);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch (Exception e){
                logger.error("wrong token attempted:", e);
            }

        }

        chain.doFilter(req, res);
    }

}
