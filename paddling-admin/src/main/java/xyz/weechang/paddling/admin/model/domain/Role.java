package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

/**
 * 角色
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_role")
public class Role extends BaseDomain {
    private static final long serialVersionUID = 3533155832022984571L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 角色名称 */
    private String roleName;

    /*** 备注 */
    private String remark;
}
