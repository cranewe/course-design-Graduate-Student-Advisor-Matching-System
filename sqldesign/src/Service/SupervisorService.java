package Service;

import entity.*;
import dao.SupervisorDao;
import Mapper.SupervisorDaoImpl;

import java.util.List;

public class SupervisorService {

    private final SupervisorDao supervisorDao;

    public SupervisorService() {
        this.supervisorDao = new SupervisorDaoImpl();
    }

    // 获取所有考生的志愿填报信息
    public void displayAllStudentPreferences() {
        List<StudentPreference> preferences = supervisorDao.getAllStudentPreferences();
        if (preferences.isEmpty()) {
            System.out.println("当前没有考生的志愿填报信息！");
        } else {
            System.out.println("所有考生填报的志愿信息：");
            for (StudentPreference preference : preferences) {
                System.out.printf("学号: %d, 姓名: %s, 第一志愿: %s, 第二志愿: %s, 第三志愿: %s\n", 
                        preference.getStudentId(), preference.getStudentName(), 
                        preference.getFirstPreference(), preference.getSecondPreference(), 
                        preference.getThirdPreference());
            }
        }
    }

    // 获取没有考生选择且存在剩余招生名额的导师信息
    public void displayAvailableProfessors() {
        List<Professor> professors = supervisorDao.getAvailableProfessors();
        if (professors.isEmpty()) {
            System.out.println("当前没有导师符合条件！");
        } else {
            System.out.println("符合条件的导师信息：");
            for (Professor professor : professors) {
                System.out.printf("导师ID: %d, 姓名: %s, 邮箱: %s, 电话: %s, 当前招生名额: %d\n", 
                        professor.getProfessorID(), professor.getName(), 
                        professor.getEmail(), professor.getPhone(), 
                        professor.getStuNumber());
            }
        }
    }

    public static void main(String[] args) {
        SupervisorService service = new SupervisorService();
        System.out.println("这里是研究生主管领导的功能实现: ");
        System.out.println("\n获取所有考生的志愿填报信息：");
        service.displayAllStudentPreferences();

        System.out.println("\n获取没有考生选择且存在剩余招生名额的导师信息：");
        service.displayAvailableProfessors();
    }
}