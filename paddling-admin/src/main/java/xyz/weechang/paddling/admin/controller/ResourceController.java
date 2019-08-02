package xyz.weechang.paddling.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.paddling.admin.model.domain.Resource;
import xyz.weechang.paddling.admin.model.domain.enums.ResourceStatusEnum;
import xyz.weechang.paddling.admin.model.domain.enums.ResourceTypeEnum;
import xyz.weechang.paddling.admin.service.IResourceService;
import xyz.weechang.paddling.core.controller.PaddlingController;
import xyz.weechang.paddling.core.model.dto.R;

import java.util.List;

/**
 * 目录管理
 *
 * @author zhangwei
 * date 2019/7/26
 * time 14:15
 */
@RestController
@RequestMapping("/paddling/admin/resource")
public class ResourceController extends PaddlingController {

    @Autowired
    private IResourceService resourceService;

    @GetMapping("toPage")
    public R toPage() {
        JSONObject result = new JSONObject();
        result.put("resourceTypes", ResourceTypeEnum.toJsonArray());
        result.put("resourceStatuses", ResourceStatusEnum.toJsonArray());
        return R.ok(result);
    }

    @GetMapping
    public R page(Page<Resource> pageParam, Resource resource) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        if (resource != null) {
            queryWrapper.eq(resource.getParentId() != null, Resource::getParentId, resource.getParentId());
            queryWrapper.like(resource.getResourceName() != null, Resource::getResourceName, resource.getResourceName());
            queryWrapper.like(resource.getResourceCode() != null, Resource::getResourceCode, resource.getResourceCode());
            queryWrapper.eq(resource.getResourceType() != null, Resource::getResourceType, resource.getResourceType());
            queryWrapper.eq(resource.getResourceStatus() != null, Resource::getResourceStatus, resource.getResourceStatus());
        }
        queryWrapper.orderByAsc(Resource::getOrderNo);
        IPage<Resource> page = resourceService.page(pageParam, queryWrapper);
        convert(page);
        return R.ok(page);
    }

    @GetMapping("{id}")
    public R detail(@PathVariable("id") Long id) {
        Resource resource = resourceService.getById(id);
        return R.ok(resource);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") Long id) {
        resourceService.removeById(id);
        return R.ok();
    }

    @PostMapping
    public R saveOrUpdate(Resource resource) {
        if (resource.getId() == null) {
            resource.setResourceStatus(ResourceStatusEnum.AVAILABLE);
        }
        resourceService.saveOrUpdate(resource);
        return R.ok();
    }

    private void convert(Resource resource) {
        resource.addExtData("resourceType", ResourceTypeEnum.getDesc(resource.getResourceType()));
        resource.addExtData("resourceStatus", ResourceStatusEnum.getDesc(resource.getResourceStatus()));
    }

    private void convert(IPage<Resource> page) {
        if (page == null) return;
        List<Resource> resources = page.getRecords();
        if (CollectionUtil.isEmpty(resources)) return;
        for (Resource resource : resources) {
            convert(resource);
        }
    }

}
