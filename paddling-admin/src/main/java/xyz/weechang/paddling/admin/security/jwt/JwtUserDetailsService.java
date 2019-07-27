package xyz.weechang.paddling.admin.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.config.PaddlingAdminAutoConfiguration;
import xyz.weechang.paddling.admin.config.PaddlingAdminProperties;
import xyz.weechang.paddling.admin.security.paddling.PaddlingUserDetailsService;

import java.util.Date;

/**
 * @author zhangwei
 * date 2019/1/28
 * time 23:14
 */
@Slf4j
@Service
public class JwtUserDetailsService extends PaddlingUserDetailsService {

    private static final String salt = "123456";

    @Autowired
    private PaddlingAdminProperties paddlingAdminProperties;

    /**
     * 登录成功
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String loginSuccess(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + paddlingAdminProperties.getTokenExpiredTime() * 1000);
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 获取用户登录信息
     *
     * @param username 用户名
     * @return 登录信息
     */
    public UserDetails getUserLoginInfo(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails != null) {

        }
        return userDetails;
    }

    /**
     * 清理用户登录信息
     *
     * @param username 用户名
     */
    public void deleteUserLoginInfo(String username) {
        // todo
    }
}
