package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.entity.Result;


public class ResultDAOImpl implements ResultDAO {

	@Override
	public void updateSelectedTable(Result ret) {
	    String sql = "INSERT INTO luquState (StudentId, ProfessorId, SubjectId, luqu_year) VALUES (?, ?, ?, ?)";
	    DAOFactory fc = new DAOFactory();
	    try (Connection conn = fc.getConn()) {
	        conn.setAutoCommit(false); // 关闭自动提交
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, ret.getStuID());
	            pstmt.setInt(2, ret.getProID());
	            pstmt.setInt(3, ret.getSubID());
	            pstmt.setDate(4, ret.getLuquDate());
	            pstmt.executeUpdate(); // 执行单条插入
	            conn.commit(); // 提交事务
	        } catch (SQLException e) {
	            conn.rollback(); // 回滚事务
	            throw new RuntimeException("更新 luquState 表失败", e);
	        } finally {
	            try {
	                conn.setAutoCommit(true); // 恢复自动提交
	            } catch (SQLException ex) {
	                ex.printStackTrace(); // 捕获恢复自动提交的异常
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("数据库连接失败", e);
	    }
	}
}