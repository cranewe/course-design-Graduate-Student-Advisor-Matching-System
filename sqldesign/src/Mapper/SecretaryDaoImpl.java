package Mapper;

import dao.SecretaryDao;
import entity.*;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SecretaryDaoImpl implements SecretaryDao {

    @Override
    public List<Student> getStudentsWithoutPreferences() {
        String sql = "SELECT StudentID, Name, Email, Phone FROM Student " +
                     "WHERE StudentID NOT IN (SELECT DISTINCT PreferenceID FROM zhiyuan);";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setEmail(rs.getString("Email"));
                student.setPhone(rs.getString("Phone"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Professor> getProfessorsWithAvailableSlots() {
        String sql = "SELECT ProfessorID, Name, Email, Phone FROM Professor WHERE StuNumber > 0;";
        List<Professor> professors = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setProfessorID(rs.getInt("ProfessorID"));
                professor.setName(rs.getString("Name"));
                professor.setEmail(rs.getString("Email"));
                professor.setPhone(rs.getString("Phone"));
                professors.add(professor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return professors;
    }
}
