package com.llu.test.dao.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * 2. 使用JDBC的java.sql.PreparedStatement执行SQL语句
 */
public class Test2 {

    public static void main(String[] args) {
        // 1.导入驱动jar包
        // 2.注册mysql8.0.11 的驱动
        // 3.获取数据库连接
        // 4.定义sql语句
        // 5.获取执行sql语句的对象
        // 6.执行sql语句
        // 7.处理结果
        // 8.释放资源
        Connection conn = null;
        try {
//            String id = "1 ' or 1=1 -- ";
            String id = "1";
            String orderBy = "name";
            int orderByInt = 1;
            String userName = "%admin%";
            conn = getConnection();
//            String sql = "select * from user where id = ? order by ?";
//           String sql = "select * from user where userName like '%" + userName + "%'";
            String sql = "select * from user where userName like ?";

            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,  userName );

//            pstmt.setString(1, id);
//            pstmt.setInt(2, orderByInt);
            ResultSet rs = pstmt.executeQuery();

            // 打印预编译之后的sql语句
            System.out.println(pstmt);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("userName") + ", Password: " + rs.getString("password"));
            }
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();

        }

    }



    public static Connection getConnection() throws Exception {
        Connection conn = null;
        // 1.导入驱动jar包
        // 2. 注册 MySQL 8.0.11 的驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 3. 获取数据库连接
        String url = "jdbc:mysql://1.92.65.242:3306/codeReview?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "admin!@#..admin";
        conn = DriverManager.getConnection(url, user, password);
        return conn;

    }

}
