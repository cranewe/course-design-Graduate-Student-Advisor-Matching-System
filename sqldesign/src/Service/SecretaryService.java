package Service;

import dao.SecretaryDao;
import Mapper.SecretaryDaoImpl;
import entity.*;
import java.util.List;

public class SecretaryService {

    private SecretaryDao secretaryDao;

    public SecretaryService() {
        this.secretaryDao = new SecretaryDaoImpl();
    }

    // 获取所有未提交导师选择志愿的考生信息
    public void displayStudentsWithoutPreferences() {
        List<Student> students = secretaryDao.getStudentsWithoutPreferences();
        if (students.isEmpty()) {
            System.out.println("当前没有未提交志愿的学生。");
        } else {
            System.out.println("以下是未提交志愿的学生信息：");
            for (Student student : students) {
                System.out.printf("学号: %d, 姓名: %s, 邮箱: %s, 电话: %s\n", 
                                  student.getStudentId(), 
                                  student.getName(), 
                                  student.getEmail(), 
                                  student.getPhone());
            }
        }
    }

    // 获取所有仍存在招生指标的导师信息
    public void displayProfessorsWithAvailableSlots() {
        List<Professor> professors = secretaryDao.getProfessorsWithAvailableSlots();
        if (professors.isEmpty()) {
            System.out.println("当前没有仍存在招生指标的导师。");
        } else {
            System.out.println("以下是仍存在招生指标的导师信息：");
            for (Professor professor : professors) {
                System.out.printf("导师ID: %d, 姓名: %s, 邮箱: %s, 电话: %s\n", 
                                  professor.getProfessorID(), 
                                  professor.getName(), 
                                  professor.getEmail(), 
                                  professor.getPhone());
            }
        }
    }

    public static void main(String[] args) {
        SecretaryService service = new SecretaryService();
        System.out.println("这里是学科秘书的功能实现：");
        // 1.显示未提交志愿的学生信息
        System.out.println("\n获取所有未提交志愿的学生信息，向其发送志愿选择提醒：");
        service.displayStudentsWithoutPreferences();

        // 2.显示仍存在招生指标的导师信息
        System.out.println("\n获取当前仍存在剩余招生指标的导师信息，向其发送提醒：");
        service.displayProfessorsWithAvailableSlots();
    }
}
