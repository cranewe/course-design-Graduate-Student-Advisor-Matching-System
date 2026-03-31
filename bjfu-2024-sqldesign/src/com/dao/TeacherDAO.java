package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Teacher;

public interface TeacherDAO {
//    Teacher getTeacherById(int id) throws SQLException;
    
    List<Teacher> getAllTeachers() throws SQLException;
}