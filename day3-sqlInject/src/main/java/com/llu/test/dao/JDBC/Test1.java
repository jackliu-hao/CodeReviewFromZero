package com.llu.test.dao.JDBC;

import java.sql.*;

/**
 * 1.使用JDBC的java.sql.Statement执行SQL语句
 */
public class Test1 {


    public static void main(String[] args) throws ClassNotFoundException {
        // 1.导入驱动jar包
        // 2.注册mysql8.0.11 的驱动
        // 3.获取数据库连接
        // 4.定义sql语句
        // 5.获取执行sql语句的对象
        // 6.执行sql语句
        // 7.处理结果
        // 8.释放资源

        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;

        try {
            // 获取链接
            conn = getConnection();
            String id = "1  or 1=1 ";

            // 4. 定义 SQL 语句
            String sql = "SELECT * FROM user WHERE id = " + id;

//            System.out.println(sql);

            // 5. 获取执行 SQL 语句的对象
            pstmt = conn.createStatement();
            System.out.println(sql);

            // 6. 执行 SQL 语句
            rs = pstmt.executeQuery(sql);

            // 7. 处理结果
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("userName") + ", Password: " + rs.getString("password"));
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 8. 释放资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources.");
                e.printStackTrace();
            }
        }

    }

    // 获取连接
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
