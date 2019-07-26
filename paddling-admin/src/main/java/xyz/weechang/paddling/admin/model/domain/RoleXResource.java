package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

/**
 * 角色-资源 关联
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_role_resource")
public class RoleXResource extends BaseDomain {
    private static final long serialVersionUID = -7172989930370294462L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 角色ID */
    private Long roleId;

    /*** 资源ID */
    private Long resourceId;
}
