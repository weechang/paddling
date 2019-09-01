package xyz.weechang.paddling.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.User;
import xyz.weechang.paddling.admin.model.domain.enums.UserStatusEnum;
import xyz.weechang.paddling.admin.service.IUserService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;

import java.util.List;

/**
 * 用户管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:16
 */
@RestController
@RequestMapping("/paddling/admin/user")
public class UserController extends PaddlingController {

    @Autowired
    private IUserService userService;

    @GetMapping("/toPage")
    public R toPage() {
        JSONObject result = new JSONObject();
        result.put("userStatuses", UserStatusEnum.toJsonArray());
        return R.ok(result);
    }

    @GetMapping
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
        this.convert(page);
        return R.ok(page);
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return R.ok(user);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        userService.removeById(id);
        return R.ok();
    }

    @PostMapping
    public R saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.setUserStatus(UserStatusEnum.AVAILABLE.getValue());
        }
        userService.saveOrUpdate(user);
        return R.ok();
    }

    @PostMapping("/bindRole")
    public R bindRole(Long userId, List<Long> roleIdList) {
        userService.bindRole(userId, roleIdList);
        return R.ok();
    }

    @PostMapping("/resetPwd/{userId}")
    public R resetPwd(@PathVariable("userId") Long userId) {
        userService.restPwd(userId);
        return R.ok();
    }

    @GetMapping("/current")
    public R current() {
        Long userId = PaddlingSecurityUtil.getUserId();
        User user = userService.getById(userId);
        return R.ok();
    }

    private void convert(User user) {
        user.addExtData("userStatus", UserStatusEnum.getDesc(user.getUserStatus()));
    }

    private void convert(IPage<User> page) {
        if (page == null) return;
        List<User> users = page.getRecords();
        if (CollectionUtil.isEmpty(users)) return;
        for (User user : users) {
            convert(user);
        }
    }
}
