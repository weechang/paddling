package xyz.weechang.paddling.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.weechang.paddling.admin.error.PaddlingAdminError;
import xyz.weechang.paddling.admin.mapper.UserMapper;
import xyz.weechang.paddling.admin.model.domain.User;
import xyz.weechang.paddling.admin.service.IUserService;
import xyz.weechang.paddling.core.exception.AppException;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/7/26
 * time 14:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getOneByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }

    @Override
    public void createUser(User user) {
        String username = user.getUsername();
        User saved = getOneByUsername(username);
        if (saved != null) {
            throw new AppException(PaddlingAdminError.USER_EXISTED);
        }
        super.save(user);
    }

    @Override
    public void bindRole(Long userId, List<Long> roleIdList) {

    }
}
