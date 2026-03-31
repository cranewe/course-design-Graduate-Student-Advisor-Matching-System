package com.dao.zcw;

import com.entity.zcw.*;

import java.util.List;

public interface AdminDao {
    // 发布录取结果
    int publishAdmissionResults();

    // 查询有错误信息的学生
    List<Student> getStudentsWithMissingInfo();

    // 查询所有考生的成绩
    List<StudentExamInfo> getAllExamInfo();
}
