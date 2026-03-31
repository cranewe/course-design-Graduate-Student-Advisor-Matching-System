package com.dao.zcw;

import com.entity.zcw.*;
import java.util.List;

public interface SupervisorDao {
    // 获取所有考生的志愿填报信息
    List<StudentPreference> getAllStudentPreferences();

    // 获取没有考生选择且存在剩余招生名额的导师信息
    List<Professor> getAvailableProfessors();
}