package xyz.weechang.paddling.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.Role;
import xyz.weechang.paddling.admin.service.IRoleService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;

import java.util.List;

/**
 * 角色管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:15
 */
@RestController
@RequestMapping("/paddling/admin/role")
public class RoleController extends PaddlingController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public R page(Page<Role> pageParam, Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (role != null) {
            queryWrapper.like(role.getRoleName() != null, Role::getRoleName, role.getRoleName());
        }
        IPage<Role> page = roleService.page(pageParam, queryWrapper);
        return R.ok(page);
    }

    @GetMapping("{id}")
    public R detail(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return R.ok(role);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") Long id) {
        roleService.removeById(id);
        return R.ok();
    }

    @PostMapping
    public R saveOrUpdate(Role role) {
        roleService.saveOrUpdate(role);
        return R.ok();
    }

    @GetMapping("/listAll")
    public R listAll(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (role != null) {
            queryWrapper.like(role.getRoleName() != null, Role::getRoleName, role.getRoleName());
        }
        List<Role> roles = roleService.list(queryWrapper);
        return R.ok(roles);
    }

    @PostMapping("bindResource")
    public R bindResource(Long roleId, List<Long> resourceIdList) {
        roleService.bindResource(roleId, resourceIdList);
        return R.ok();
    }
}
