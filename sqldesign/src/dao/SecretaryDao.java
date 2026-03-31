package dao;

import entity.*;
import java.util.List;

public interface SecretaryDao {
    // 获取所有未提交导师选择志愿的考生信息
    List<Student> getStudentsWithoutPreferences();

    // 获取所有仍存在招生指标的导师信息
    List<Professor> getProfessorsWithAvailableSlots();
}