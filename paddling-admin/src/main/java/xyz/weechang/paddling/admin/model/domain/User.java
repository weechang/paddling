package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

import java.util.Date;

/**
 * 用户信息
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_user")
public class User extends BaseDomain {
    private static final long serialVersionUID = -1810925369537831783L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 用户名 */
    private String username;

    /*** 密码 */
    @JsonIgnore
    private String password;

    /*** 盐 */
    private String salt;

    /*** 昵称 */
    private String nickName;

    /*** 头像 */
    private String avatar;

    /*** 邮箱 */
    private String email;

    /*** 手机号 */
    private String mobile;

    /*** 尝试次数 */
    private Integer triedTimes;

    /*** 锁定时间 */
    private Date lockedTime;

    /*** 用户状态  0：禁用 1：正常  2：锁定 */
    private Integer userStatus;
}
