package com.dao;

import java.sql.*;
import java.util.List;

import com.entity.Student;

public class StudentDAOTest{
    public static void main(String[] args) {
    	DAOFactory fc = new DAOFactory();
    	Connection conn = fc.getConn();
            
            StudentDAO studentDAO = new StudentDAOImpl(conn);

            List<Student> students = studentDAO.getAllStudents();
            
            if(students == null) {
            	System.out.println("empty stu list");
            	return;
            }
            
            for(Student stu : students) {
            	System.out.println(stu);
            }
    }
}