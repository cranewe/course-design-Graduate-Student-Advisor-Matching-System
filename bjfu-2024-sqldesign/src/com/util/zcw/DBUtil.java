package com.util.zcw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 数据库连接参数
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SQLDesign;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "Tony";
    private static final String DB_PASSWORD = "1";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  

    static {
        try {
            Class.forName(JDBC_DRIVER);  
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库驱动加载失败", e);
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        System.out.println("尝试连接数据库...");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
        // 验证连接是否有效，timeout 设置为 5 秒
        if (conn != null && conn.isValid(5)) {
            System.out.println("数据库连接成功！");
        } else {
            System.out.println("数据库连接失败！");
        }
        
        return conn;
    }

    // 关闭数据库连接
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("数据库连接已关闭！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
