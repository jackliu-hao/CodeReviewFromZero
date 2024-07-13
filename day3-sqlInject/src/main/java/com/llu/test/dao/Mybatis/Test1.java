package com.llu.test.dao.Mybatis;

import com.llu.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test1 {
    SqlSessionFactory sqlSessionFactory = null;
    SqlSession sqlSession = null;
    {
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            // 通过资源路径获取Reader对象，用于读取MyBatis的配置文件
            reader = org.apache.ibatis.io.Resources.getResourceAsReader(resource);
            // 使用SqlSessionFactoryBuilder根据配置文件构建SqlSessionFactory对象
            // SqlSessionFactory是MyBatis的核心对象，用于创建SqlSession
            sqlSessionFactory = new org.apache.ibatis.session.SqlSessionFactoryBuilder().build(reader);
            // 打开一个新的SqlSession，用于执行SQL操作
            sqlSession = sqlSessionFactory.openSession();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testSelectser(){
//        User user = sqlSession.selectOne("com.llu.mapper.UserMapper.selectUser", "1");
//        List<User> users = sqlSession.selectList("com.llu.mapper.UserMapper.selectAllUser", "userName");
        List<User> users = sqlSession.selectList("com.llu.mapper.UserMapper.selectAllUserTestLike", "admin");

        System.out.println(users);

    }

    public static void main(String[] args) {
        new Test1().testSelectser();
    }
}
