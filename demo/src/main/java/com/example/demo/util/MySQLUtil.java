package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {

    /**
     * 获取 MySQL 数据库连接（每次调用都新建连接，用完即关）
     */
    public static Connection getConnection() {
        try {
            String url = ConfigUtil.getProperty("mysql.url"); // 例如 jdbc:mysql://localhost:3306/your_database
            String username = ConfigUtil.getProperty("mysql.username");
            String password = ConfigUtil.getProperty("mysql.password");

            // 加载 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 创建新连接
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载 MySQL 驱动失败", e);
        } catch (SQLException e) {
            throw new RuntimeException("连接 MySQL 数据库失败", e);
        }
    }
}