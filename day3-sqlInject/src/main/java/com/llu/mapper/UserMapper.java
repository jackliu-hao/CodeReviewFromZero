package com.llu.mapper;

import com.llu.bean.User;

import java.util.List;

public interface UserMapper {

     User selectUser(String id);

     List<User> selectAllUser(String orderBy);

     List<User> selectAllUserTestLike(String like);

}
