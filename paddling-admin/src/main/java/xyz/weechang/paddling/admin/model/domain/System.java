package xyz.weechang.paddling.admin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.paddling.core.model.domain.BaseDomain;

/**
 * 系统信息
 *
 * @author zhangwei
 * date 2019/7/26
 * time 13:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "paddling_admin_system")
public class System extends BaseDomain {
    private static final long serialVersionUID = -8183357682377288315L;

    /*** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /*** 系统名称 */
    private String sysName;

    /*** 系统图标 */
    private String sysIcon;

    /*** 系统域名 */
    private String sysDomain;

    /*** 排序 */
    private Integer sortNo;
}
