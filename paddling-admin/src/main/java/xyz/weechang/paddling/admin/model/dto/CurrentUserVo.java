package xyz.weechang.paddling.admin.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/7/30
 * time 17:27
 */
@Data
public class CurrentUserVo implements Serializable {
    private static final long serialVersionUID = -5646303688619497783L;

    /*** 用户名 */
    private String username;

    /*** 昵称 */
    private String nickName;

    /*** 头像 */
    private String avatar;

    /*** 邮箱 */
    private String email;

    /*** 手机号 */
    private String mobile;
}
