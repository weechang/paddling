package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

/**
 * 用户-角色关联
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_user_role")
public class UserXRole extends BaseDomain {
    private static final long serialVersionUID = 7670464597391527087L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 用户ID */
    private Long userId;

    /*** 角色ID */
    private Long roleId;
}
