package com.dao;
import java.util.List;
import com.entity.Student;

public interface StudentDAO {
    List<Student> getAllStudents(); // 查询所有学生
}