package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.admin.model.domain.enums.ResourceTypeEnum;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

/**
 * 资源
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_resource")
public class Resource extends BaseDomain {
    private static final long serialVersionUID = 8231110881270468750L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 系统Id */
    private Long sysId;

    /*** 上级Id */
    private String parenId;

    /*** 资源名称 */
    private String resourceName;

    /*** 路径 */
    private String url;

    /*** 资源类型 1:菜单 2:页面组件 3:请求路径 4:外链菜单 */
    private ResourceTypeEnum resourceType;

    /*** 资源图标 */
    private String resourceIcon;

    /*** 排序 */
    private Integer sortNo;

    /*** 备注 */
    private String remark;
}
