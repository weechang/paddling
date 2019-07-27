package xyz.weechang.paddling.admin.security.paddling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.admin.service.IPaddlingSecurityService;
import xyz.weechang.paddling.core.exception.AppException;
import xyz.weechang.paddling.core.security.PaddlingSecurityUser;

/**
 * UserDetailsService 实现
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:49
 */
public class PaddlingUserDetailsService implements UserDetailsService {

    @Autowired
    protected IPaddlingSecurityService paddlingSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PaddlingSecurityUser user = paddlingSecurityService.findFirstByUsername(username);
        if (user == null) {
            throw new AppException(PaddlingAdminError.USER_NOT_FOUNT);
        }
        return new PaddlingUserDetails(user.getUsername(), user.getPassword());
    }
}
