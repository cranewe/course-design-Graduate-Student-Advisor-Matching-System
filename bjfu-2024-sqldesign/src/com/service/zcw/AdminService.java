package com.service.zcw;

import com.dao.zcw.AdminDao;
import com.dao.zcw.AdminDaoImpl;
import com.entity.zcw.Student;
import com.entity.zcw.StudentExamInfo;

import java.util.List;

public class AdminService {
    public static void main(String[] args) {
        System.out.println("这里是研究生管理秘书的功能实现：");

        AdminDao adminDao = new AdminDaoImpl();

        // 查询所有考生的初试与复试成绩
        System.out.println("\n查询所有考生的初试与复试成绩(升序)");
        List<StudentExamInfo> examInfos = adminDao.getAllExamInfo();
        System.out.println("学号\t姓名\t邮箱\t\t电话\t\t初试成绩\t复试成绩");
        for (StudentExamInfo info : examInfos) {
            System.out.printf("%d\t%s\t%s\t%s\t%.2f\t%.2f\n",
                    info.getStudentId(), info.getName(), info.getEmail(), info.getPhone(),
                    info.getChushiScore(), info.getFushiScore());
        }

        // 筛选出所有存在错误信息的学生
        System.out.println("\n检测出所有存在错误信息的学生");
        List<Student> errorStudents = adminDao.getStudentsWithMissingInfo();
        if (errorStudents.isEmpty()) {
            System.out.println("当前不存在有错误信息的学生！");
        } else {
            System.out.println("学号\t姓名\t邮箱\t\t\t电话");
            for (Student student : errorStudents) {
                System.out.printf("%d\t%s\t%s\t%s\n",
                        student.getStudentId(), student.getName(), student.getEmail(), student.getPhone());
            }
        }

        // 向数据库推送录取结果
        System.out.println("\n向数据库推送录取结果");
        int affectedRows = adminDao.publishAdmissionResults();
        if (affectedRows > 0) {
            System.out.println("录取结果已成功推送到数据库！");
        } else {
            System.out.println("当前没有已录取的学生！");
        }
    }
}
