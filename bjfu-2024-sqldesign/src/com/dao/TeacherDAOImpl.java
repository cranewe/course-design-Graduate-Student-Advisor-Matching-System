package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Teacher;

public class TeacherDAOImpl implements TeacherDAO {

    private final Connection conn;

    // Constructor to initialize connection
    public TeacherDAOImpl(Connection conn) {
        this.conn = conn;
    }

//    @Override
//    public Teacher getTeacherById(int id) throws SQLException {
//        String query = "SELECT * FROM ProfessorSubjectView WHERE id = ?";
//        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                if (rs.next()) {
//                    Teacher teacher = new Teacher(
//                    		rs.getInt("id"),
//                    		rs.getString("name"),
//                    		rs.getString("sec_subject"),
//                    		rs.getString("fst_subject"),
//                    		rs.getInt("left_num"));
//                    return teacher;
//                }
//            }
//        }
//        return null; // If no teacher is found
//    }
    
    @Override 
    public List<Teacher> getAllTeachers() throws SQLException{
    	String query = "SELECT * FROM ProfessorSubjectView";
    	List<Teacher> teachList = new ArrayList<Teacher>();
    	try(PreparedStatement prstmt = conn.prepareStatement(query)){
    		try(ResultSet rs = prstmt.executeQuery()){
    			while(rs.next()) {
    				teachList.add(new Teacher(
    						rs.getInt("id"),
    						rs.getInt("SubID"),
    						rs.getString("name"),
    						rs.getString("fstSubject"),
    						rs.getString("secSubject"),
    						rs.getInt("leftNum")
    						));
    			}
    		}
    	}
    	return teachList;
    }

}