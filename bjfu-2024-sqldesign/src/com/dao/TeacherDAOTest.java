package com.dao;

import com.entity.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TeacherDAOTest {
    public static void main(String[] args) {
        Connection conn = new DAOFactory().getConn();
        try{
            TeacherDAOImpl teacherDAO = new TeacherDAOImpl(conn);
            List<Teacher> teachers = teacherDAO.getAllTeachers();

            if(teachers == null)
            {
            	System.out.println("teacher list is null");
            	return;
            }else {
            	for(Teacher tea : teachers) {
            		System.out.println(tea);
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}