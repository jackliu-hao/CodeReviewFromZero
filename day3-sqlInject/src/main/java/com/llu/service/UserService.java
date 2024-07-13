package com.llu.service;

import com.llu.entity.User;


/**
 * (User)表服务接口
 *
 * @author 11u
 * @since 2024-07-12 22:42:55
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Boolean register(User user);

    User login(User user);

    User queryByLoginName(String loginName);
}
