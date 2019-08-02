package xyz.weechang.paddling.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.weechang.paddling.admin.model.domain.User;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:09
 */
public interface IUserService extends IService<User> {

    User getOneByUsername(String username);

    void bindRole(Long userId, List<Long> roleIdList);

    void restPwd(Long userId);
}
