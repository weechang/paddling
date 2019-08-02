package xyz.weechang.paddling.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.admin.mapper.ResourceMapper;
import xyz.weechang.paddling.admin.model.domain.Resource;
import xyz.weechang.paddling.admin.service.IResourceService;
import xyz.weechang.paddling.core.exception.AppException;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public boolean saveOrUpdate(Resource resource) {
        Long parentId = resource.getParentId();
        String resourceName = resource.getResourceName();
        String resourceCode = resource.getResourceCode();
        Resource saved = getOne(parentId, resourceName, resourceCode);
        if (saved != null && !saved.getId().equals(resource.getId())) throw new AppException(PaddlingAdminError.RESOURCE_EXISTED);
        return super.saveOrUpdate(resource);
    }

    private Resource getOne(Long parentId, String resourceName, String resourceCode) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resource::getParentId, parentId).and(
                a -> a.eq(Resource::getResourceName, resourceName).or(
                        o -> o.eq(Resource::getResourceCode, resourceCode)
                )
        );
        return this.getOne(queryWrapper);
    }
}
