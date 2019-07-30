package xyz.weechang.paddling.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.paddling.core.model.domain.BaseDomain;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;
import xyz.weechang.paddling.core.service.IPaddlingService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * @author zhangwei
 * date 2019/7/29
 * time 12:23
 */
public class PaddlingServiceImpl<M extends BaseMapper<T>, T extends BaseDomain> extends ServiceImpl<M, T> implements IPaddlingService<T> {

    @Override
    public boolean save(T entity){
        entity.setCreatedBy(PaddlingSecurityUtil.getUserId());
        entity.setCreatedDate(new Date());
        return super.save(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        Long userId = PaddlingSecurityUtil.getUserId();
        Date now = new Date();
        if (CollectionUtil.isNotEmpty(entityList)) {
            for (T entity : entityList) {
                entity.setCreatedBy(userId);
                entity.setCreatedDate(now);
            }
        }
        return super.saveBatch(entityList, batchSize);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdate(T entity) {
        if (entity == null) return false;
        Class<?> cls = entity.getClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
        boolean idValNull = StringUtils.checkValNull(idVal) || Objects.isNull(this.getById((Serializable)idVal));
        if (idValNull) {
            entity.setCreatedBy(PaddlingSecurityUtil.getUserId());
            entity.setCreatedDate(new Date());
        } else {
            entity.setModifiedBy(PaddlingSecurityUtil.getUserId());
            entity.setModifiedDate(new Date());
        }
        return super.saveOrUpdate(entity);
    }

}
