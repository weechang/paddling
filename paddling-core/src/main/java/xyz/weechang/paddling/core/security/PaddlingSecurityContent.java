package xyz.weechang.paddling.core.security;

/**
 * 安全信息
 *
 * @author zhangwei
 * date 2019/1/30
 * time 23:16
 */
public class PaddlingSecurityContent {

    private static volatile PaddlingSecurityContent instance;

    public static PaddlingSecurityContent getInstance() {
        if (instance == null) {
            synchronized (PaddlingSecurityContent.class) {
                if (instance == null) {
                    instance = new PaddlingSecurityContent();
                }
            }
        }
        return instance;
    }

    private ThreadLocal<String> username = new ThreadLocal<>();
    private ThreadLocal<Long> userId = new ThreadLocal<>();

    /**
     * 设置登录信息
     *
     * @param username 用户名
     * @param userId   用户id
     */
    public void setLoginInfo(String username, Long userId) {
        this.username.set(username);
        this.userId.set(userId);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return this.username.get();
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public Long getUserId() {
        return this.userId.get();
    }

    /**
     * 登出
     */
    public void logout() {
        this.username.remove();
        this.userId.remove();
    }

}
