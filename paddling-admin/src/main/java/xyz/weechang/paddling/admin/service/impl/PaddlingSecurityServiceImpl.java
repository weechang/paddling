package xyz.weechang.paddling.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.model.domain.User;
import xyz.weechang.paddling.admin.service.IPaddlingSecurityService;
import xyz.weechang.paddling.admin.service.IUserService;
import xyz.weechang.paddling.core.security.PaddlingSecurityUser;

@Service
public class PaddlingSecurityServiceImpl implements IPaddlingSecurityService {

    @Autowired
    private IUserService userService;

    @Override
    public boolean isUrlPermissionByName(String username, String url) {
        return true;
    }

    @Override
    public PaddlingSecurityUser findFirstByUsername(String username) {
        PaddlingSecurityUser securityUser = new PaddlingSecurityUser();
        // 此处应根据自身业务情况进行
        User user = userService.getOneByUsername(username);
        if (user != null) {
            securityUser.setUsername(user.getUsername());
            securityUser.setPassword(user.getPassword());
        }
        return securityUser;
    }
}
