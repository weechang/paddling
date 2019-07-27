package xyz.weechang.paddling.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.System;
import xyz.weechang.paddling.admin.service.ISystemService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;

/**
 * 系统管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:16
 */
@RestController
@RequestMapping("/system")
public class SystemController extends PaddlingController {

    @Autowired
    private ISystemService systemService;

    /**
     * 分页查询用户信息
     *
     * @param pageParam 分页参数
     * @param system      查询参数
     * @return 分页结果
     */
    @GetMapping("page")
    public R page(Page<System> pageParam, System system) {
        LambdaQueryWrapper<System> queryWrapper = new LambdaQueryWrapper<>();
        if (system != null) {
            queryWrapper.like(system.getSysName() != null, System::getSysName, system.getSysName());
        }
        IPage<System> page = systemService.page(pageParam, queryWrapper);
        return R.ok(page);
    }

    /**
     * 获取系统详情
     *
     * @param id 用户id
     * @return 系统详情
     */
    @GetMapping("detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        System system = systemService.getById(id);
        return R.ok(system);
    }

    /**
     * 删除系统
     * @param id 系统ID
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        systemService.removeById(id);
        return R.ok();
    }

    /**
     * 保存系统
     * @param system 系统
     * @return 系统
     */
    @PostMapping
    public R save(@RequestBody System system){
        return null;
    }
}
