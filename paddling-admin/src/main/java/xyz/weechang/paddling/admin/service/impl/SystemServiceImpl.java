package xyz.weechang.paddling.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.mapper.SystemMapper;
import xyz.weechang.paddling.admin.model.domain.System;
import xyz.weechang.paddling.admin.service.ISystemService;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:12
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements ISystemService {

    @Override
    public boolean saveOrUpdate(System system) {
        if (system.getId() == null) {

        }
        return super.saveOrUpdate(system);
    }
}
