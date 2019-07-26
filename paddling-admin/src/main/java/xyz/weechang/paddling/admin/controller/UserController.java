package xyz.weechang.paddling.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.User;
import xyz.weechang.paddling.admin.service.IUserService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;

import java.util.List;

/**
 * 用户管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:16
 */
@RestController
@RequestMapping("/user")
public class UserController extends PaddlingController {

    @Autowired
    private IUserService userService;

    /**
     * 分页查询用户信息
     *
     * @param pageParam 分页参数
     * @param user      查询参数
     * @return 分页结果
     */
    @GetMapping("page")
    public R page(Page<User> pageParam, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (user != null) {
            queryWrapper.like(user.getUsername() != null, User::getUsername, user.getUsername());
            queryWrapper.like(user.getNickName() != null, User::getNickName, user.getNickName());
            queryWrapper.like(user.getEmail() != null, User::getEmail, user.getEmail());
            queryWrapper.like(user.getMobile() != null, User::getMobile, user.getMobile());
            queryWrapper.like(user.getUserStatus() != null, User::getUserStatus, user.getUserStatus());
        }
        IPage<User> page = userService.page(pageParam, queryWrapper);
        return R.ok(page);
    }

    /**
     * 获取用户详情
     *
     * @param id 用户id
     * @return 用户详情
     */
    @GetMapping("detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return R.ok(user);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        userService.removeById(id);
        return R.ok();
    }

    /**
     * 创建用户
     * @param username 用户名
     * @return 创建结果
     */
    @PostMapping("createUser")
    public R createUser(String username) {
        User user = new User();
        user.setUsername(username);
        userService.createUser(user);
        return R.ok();
    }

    /**
     * 绑定角色
     * @return 绑定结果
     */
    @PostMapping("bindRole")
    public R bindRole(Long userId, List<Long> roleIdList) {
        userService.bindRole(userId, roleIdList);
        return R.ok();
    }

}
