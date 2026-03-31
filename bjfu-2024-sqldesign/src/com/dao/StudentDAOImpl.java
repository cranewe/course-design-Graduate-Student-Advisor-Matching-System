package com.dao;
// StudentDAOImpl.java
/**
 * StudentDAO的作用是读取所有学生实体
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.entity.Student;

public class StudentDAOImpl implements StudentDAO {
//    private Connection connection;
    
    private Connection conn;
    
    public StudentDAOImpl(Connection _conn) {
    	this.conn = _conn;
    }
    
    @Override
    public List<Student> getAllStudents() {			// 
    	System.out.println("StudentDAOImpl.getAllStudents");
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM stu_info";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
            	// 复试通过了才可以选择
            	if(rs.getString("luquState").contentEquals("2")) {
            		String[] p = new String[3];	// 二维数组这里不会出错吧
                	p[0] = rs.getString("p1");
                	p[1] = rs.getString("p2");
                	p[2] = rs.getString("p3");
                    students.add(new Student(
                    		rs.getInt("id"),
                    		rs.getString("name"),
                    		p,
                            rs.getDouble("fstScore"),
                            rs.getDouble("secScore"),
                            rs.getString("schoolName"),
                            rs.getString("major"),
                            rs.getString("fstSubject"),
                            rs.getString("secSubject")
                        ));
            	}
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
//    // 构造方法传入数据库连接
//    public StudentDAOImpl(Connection connection) {
//        this.connection = connection;
//    }
    
//    @Override
//    public void addStudent(Student _stu) {
//    	String sql = "insert into Student (name, schoolName, major) VALUES (?, ?, ?)";
//    	try(PreparedStatement pstmt = conn.prepareStatement(sql)){
//    		pstmt.setString(1, _stu.getName());
//    		pstmt.setString(2, _stu.getSchoolName());
//    		pstmt.setString(3,  _stu.getMajor());
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//    }

//    @Override
//    public void addStudent(Student student) {
//        String sql = "INSERT INTO Student (id, name, age) VALUES (?, ?, ?)";
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, student.getId());
//            pstmt.setString(2, student.getName());
//            pstmt.setInt(3, student.getAge());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public Student getStudentById(int id) {
//    	System.out.println("StudentDAOImpl.getStudentById: ");
//        String sql = "SELECT * FROM stu_info WHERE studentID = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                	String[] p = new String[3];	// 二维数组这里不会出错吧
//                	p[0] = rs.getString("p1");
//                	p[1] = rs.getString("p2");
//                	p[2] = rs.getString("p3");
//                	Student student = new Student(
//                    		rs.getString("name"),
//                    		p,
//                            rs.getDouble("grade1"),
//                            rs.getDouble("grade2"),
//                            rs.getString("school"),
//                            rs.getString("major")
//                        );
//                	System.out.println("Student：\t" + student);
//                    return student;
//                } else {
//                	System.out.println("Sorry Student: " + id + " not found");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



//    @Override
//    public void updateStudent(Student student) {
//        String sql = "UPDATE Student SET name = ?, age = ? WHERE id = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, student.getName());
//            pstmt.setInt(2, student.getAge());
//            pstmt.setInt(3, student.getId());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteStudent(int id) {
//        String sql = "DELETE FROM Student WHERE id = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}