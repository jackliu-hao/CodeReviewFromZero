package com.llu.mapper;

import com.llu.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

     User selectUser(String id);

     List<User> selectAllUser(String orderBy);

     List<User> selectAllUserTestLike(String like);

     List<User> selectAllUserTestIn( String inName);


}
