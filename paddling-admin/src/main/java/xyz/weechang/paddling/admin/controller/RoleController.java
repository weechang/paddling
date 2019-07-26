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
@RequestMapping("/role")
public class RoleController extends PaddlingController {

    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询角色信息
     *
     * @param pageParam 分页参数
     * @param role      查询参数
     * @return 分页结果
     */
    @GetMapping("page")
    public R page(Page<Role> pageParam, Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (role != null) {
            queryWrapper.like(role.getRoleName() != null, Role::getRoleName, role.getRoleName());
        }
        IPage<Role> page = roleService.page(pageParam, queryWrapper);
        return R.ok(page);
    }

    /**
     * 获取角色详情
     *
     * @param id 角色id
     * @return 角色详情
     */
    @GetMapping("detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return R.ok(role);
    }

    /**
     * 删除角色
     * @param id 角色ID
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        roleService.removeById(id);
        return R.ok();
    }

    /**
     * 绑定资源
     * @return 绑定结果
     */
    @PostMapping("bindRole")
    public R bindRole(Long roleId, List<Long> resourceIdList) {
        roleService.bindResource(roleId, resourceIdList);
        return R.ok();
    }
}
