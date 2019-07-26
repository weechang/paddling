package xyz.weechang.paddling.core.controller;

import xyz.weechang.paddling.core.exception.AppException;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:13.
 */
public class PaddlingController {

    protected String getUsername() {
        String username = PaddlingSecurityUtil.getUsername();
        if (username == null) {
            throw new AppException();
        }
        return PaddlingSecurityUtil.getUsername();
    }

}
