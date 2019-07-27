package xyz.weechang.paddling.admin.error;

import xyz.weechang.paddling.core.error.IError;

/**
 * Admin 错误
 *
 * @author zhangwei
 * date 2018/10/27
 * time 18:18
 */
public enum PaddlingAdminError implements IError {
    USER_EXISTED(1001, "用户已存在"),
    MENU_EXISTED(1002, "目录已存在"),
    ROLE_EXISTED(1003, "角色已存在"),
    ROLE_NOT_EXISTED(1004, "角色不存在"),
    ACCESS_FORBIDDEN(2001, "权限不足"),
    USER_NOT_FOUNT(2001, "用户不存在"),
    USER_NOT_LOGIN(2003, "用户未登录"),
    LOGIN_ERROR(2004, "登录失败"),
    ;

    int code;
    String msg;
    private static final String ns = "Paddling-Admin";

    PaddlingAdminError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getNs() {
        return ns;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
