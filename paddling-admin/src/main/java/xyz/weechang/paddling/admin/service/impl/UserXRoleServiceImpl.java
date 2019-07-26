package xyz.weechang.paddling.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.mapper.UserXRoleMapper;
import xyz.weechang.paddling.admin.model.domain.UserXRole;
import xyz.weechang.paddling.admin.service.IUserXRoleService;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 16:07
 */
@Service
public class UserXRoleServiceImpl extends ServiceImpl<UserXRoleMapper, UserXRole> implements IUserXRoleService {
}
