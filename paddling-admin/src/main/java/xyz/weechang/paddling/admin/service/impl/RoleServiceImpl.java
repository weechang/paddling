package xyz.weechang.paddling.admin.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.admin.mapper.RoleMapper;
import xyz.weechang.paddling.admin.mapper.RoleXResourceMapper;
import xyz.weechang.paddling.admin.model.domain.Role;
import xyz.weechang.paddling.admin.service.IRoleService;
import xyz.weechang.paddling.core.exception.AppException;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleXResourceMapper roleXResourceMapper;

    @Override
    public void bindResource(Long roleId, List<Long> resourceIdList) {
        Role savedRole = super.getById(roleId);
        if (savedRole == null) {
            throw new AppException(PaddlingAdminError.ROLE_NOT_EXISTED);
        }
        JSONObject resourceIdJson = JSONUtil.parseObj(resourceIdList);

    }

    @Override
    public boolean saveOrUpdate(Role role) {
        return super.saveOrUpdate(role);
    }
}
