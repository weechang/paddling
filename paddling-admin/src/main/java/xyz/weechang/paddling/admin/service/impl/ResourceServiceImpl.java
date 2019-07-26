package xyz.weechang.paddling.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.mapper.ResourceMapper;
import xyz.weechang.paddling.admin.model.domain.Resource;
import xyz.weechang.paddling.admin.service.IResourceService;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
}
