package xyz.weechang.paddling.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.Resource;
import xyz.weechang.paddling.admin.service.IResourceService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;

/**
 * 目录管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:15
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends PaddlingController {

    @Autowired
    private IResourceService resourceService;

    /**
     * 分页查询资源信息
     *
     * @param pageParam 分页参数
     * @param resource  查询参数
     * @return 分页结果
     */
    @GetMapping("page")
    public R page(Page<Resource> pageParam, Resource resource) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        if (resource != null) {
            queryWrapper.eq(resource.getSysId() != null, Resource::getSysId, resource.getSysId());
            queryWrapper.like(resource.getResourceName() != null, Resource::getResourceName, resource.getResourceName());
            queryWrapper.eq(resource.getResourceType() != null, Resource::getResourceType, resource.getResourceType());
        }
        IPage<Resource> page = resourceService.page(pageParam, queryWrapper);
        return R.ok(page);
    }

    /**
     * 获取资源详情
     *
     * @param id 资源id
     * @return 资源详情
     */
    @GetMapping("detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Resource resource = resourceService.getById(id);
        return R.ok(resource);
    }

    /**
     * 删除资源
     *
     * @param id 资源ID
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        resourceService.removeById(id);
        return R.ok();
    }

    public R save(Resource resource){
        return null;
    }
}
