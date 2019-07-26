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
    USER_EXISTED(1, "用户已存在"),
    MENU_EXISTED(2, "目录已存在"),
    ROLE_EXISTED(3, "角色已存在"),
    ROLE_NOT_EXISTED(4, "角色不存在"),
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
