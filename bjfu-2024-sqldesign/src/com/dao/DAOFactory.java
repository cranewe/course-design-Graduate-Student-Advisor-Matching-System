package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    // 提取数据库配置为常量，便于维护
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=SQLDesign;encrypt=false";
    private static final String DB_USER = "tony";
    private static final String DB_PASSWORD = "1";

    static {
        try {
            // 显式加载 SQL Server 驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("SQL Server JDBC 驱动加载成功！");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQL Server JDBC 驱动加载失败！", e);
        }
    }

    /**
     * 获取数据库连接
     * @return Connection对象，如果失败返回null
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn 待关闭的Connection对象
     */
    public void disconnected(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("数据库连接已关闭！");
            } catch (SQLException e) {
                System.err.println("关闭数据库连接失败！");
                e.printStackTrace();
            }
        }
    }
}
