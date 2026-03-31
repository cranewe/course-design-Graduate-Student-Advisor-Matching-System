package Service;

import dao.LeaderDao;
import Mapper.LeaderDaoImpl;
import entity.*;

import java.util.List;

public class LeaderService {
    public static void main(String[] args) {
        LeaderDao leaderDao = new LeaderDaoImpl();
        System.out.println("这里是学科负责人的功能实现！");

        // 1. 获取所有拟录取学生信息
        System.out.println("获取所有拟录取学生的初试成绩，复试成绩，学历信息：");
        List<StudentExamInfo> admittedStudents = leaderDao.getAllAdmittedStudents();
        if(admittedStudents.isEmpty())
        {
        	System.out.println("当前没有拟录取的学生！");
        }
        else
        {
            for (StudentExamInfo student : admittedStudents) {
                System.out.printf("学号: %d, 姓名: %s, 邮箱: %s, 电话: %s, 学校: %s, 学校类型: %s, 专业： %s, 初试总分: %.2f, 复试总分: %.2f%n",
                        student.getStudentId(), student.getName(), student.getEmail(), student.getPhone(),
                        student.getSchool(), student.getSchoolType(), student.getMajor(),student.getChushiScore(), student.getFushiScore());
            }
        }

        // 2. 获取某导师志愿学生信息
        int professorId = 106; 
        System.out.println("\n获取导师 " + professorId + " 的所有填报的志愿学生信息：");
        List<StudentPreference> studentsByPreferences = leaderDao.getStudentsByAdvisorPreferences(professorId);
        if(studentsByPreferences.isEmpty())
        {
        	System.out.println("导师"+ professorId + "当前并没有志愿学生信息！");
        }
        else
        {
            for (StudentPreference student : studentsByPreferences) {
                System.out.printf("志愿类别: %s, 学号: %d%n", student.getPreference(), student.getStudentId());
            }
        }
        
        // 3. 获取所有导师学生分配信息
        System.out.println("\n获取最终所有导师学生分配信息：");
        List<StudentAssignment> advisorAssignments = leaderDao.getAllAdvisorAssignments();
        if(advisorAssignments.isEmpty())
        {
        	System.out.println("当前导师学生分配还未结束！请耐心等待！");
        }
        else
        {
            for (StudentAssignment assignment : advisorAssignments) {
                System.out.printf("学号: %d, 学生姓名: %s, 导师ID: %d, 导师姓名: %s, 科目ID: %d, 科目名称: %s, 录取年份: %tY%n",
                        assignment.getStudentId(), assignment.getStudentName(), assignment.getProfessorID(),
                        assignment.getProfessorName(), assignment.getSubjectID(), assignment.getSubjectName(),
                        assignment.getLuquyear());
            }
        }
       
    }
}
