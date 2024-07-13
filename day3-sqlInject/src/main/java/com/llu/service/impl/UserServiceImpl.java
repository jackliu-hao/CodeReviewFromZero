package com.llu.service.impl;

import com.llu.entity.User;
import com.llu.mapper.UserDao;
import com.llu.service.UserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author 11u
 * @since 2024-07-12 22:42:55
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        user.setUsername(UUID.randomUUID().toString());
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public Boolean register(User user) {
        // 先根据loginName查询用户
        User user1 = userDao.login(user);
        if (user1 == null){
            user.setUsername(UUID.randomUUID().toString());
            int result  = userDao.insert(user);
            return  result > 0;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User user1 = userDao.login(user);
        return user1;
    }

    @Override
    public User queryByLoginName(String loginName) {
        User user = userDao.queryByLoginName(loginName);

        return user;
    }
}
