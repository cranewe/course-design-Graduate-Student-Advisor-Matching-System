package com.dao;

import java.sql.*;

public class ConnTest{
	public static void main(String[] args) {
		Connection conn = new DAOFactory().getConn();
		String sql = "Select * from ProfessorSubjectView";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
        	while(rs.next()) {
        		System.out.println("id: " + rs.getInt("id") + "\tname: " + rs.getString("name"));
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }
	}
}