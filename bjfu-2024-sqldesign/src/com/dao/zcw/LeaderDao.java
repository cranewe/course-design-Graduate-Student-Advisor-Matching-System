package com.dao.zcw;

import com.entity.zcw.*;


import java.util.List;

public interface LeaderDao {
    List<StudentExamInfo> getAllAdmittedStudents(); // 获取所有拟录取学生的信息

    List<StudentPreference> getStudentsByAdvisorPreferences(int advisorId); // 查询某个导师志愿的学生信息

    List<StudentAssignment> getAllAdvisorAssignments(); // 获取所有导师学生分配信息
}
