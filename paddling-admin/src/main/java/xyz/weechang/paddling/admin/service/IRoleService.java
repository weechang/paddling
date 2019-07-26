package xyz.weechang.paddling.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.weechang.paddling.admin.model.domain.Role;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:08
 */
public interface IRoleService extends IService<Role> {

    void bindResource(Long roleId, List<Long> resourceIdList);
}
