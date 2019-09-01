package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    /*** 上级Id */
    private Long parentId;

    /*** 资源名称 */
    private String resourceName;

    /*** 资源编码 */
    private String resourceCode;

    /*** 路径 */
    private String url;

    /*** 资源图标 */
    private String resourceIcon;

    /*** 资源类型 1:显示菜单 2:隐藏菜单 3:页面组件 4:请求路径 5:外链菜单 */
    private Integer resourceType;

    /*** 资源状态 0:禁用 1:启用 */
    private Integer resourceStatus;

    /*** 排序 */
    private Integer orderNo;

    /*** 备注 */
    private String remark;
}
