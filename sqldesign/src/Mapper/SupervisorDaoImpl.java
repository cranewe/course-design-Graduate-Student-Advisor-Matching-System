package Mapper;

import entity.*;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.SupervisorDao;

public class SupervisorDaoImpl implements SupervisorDao {

    @Override
    public List<StudentPreference> getAllStudentPreferences() {
        String sql = "SELECT s.StudentID, s.Name AS StudentName, " +
                     "z.FirstPreference, z.SecondPreference, z.ThirdPreference " +
                     "FROM Student s " +
                     "LEFT JOIN zhiyuan z ON s.StudentID = z.PreferenceID;";

        List<StudentPreference> preferences = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StudentPreference preference = new StudentPreference();
                preference.setStudentId(rs.getInt("StudentID"));
                preference.setStudentName(rs.getString("StudentName"));
                preference.setFirstPreference(rs.getString("FirstPreference"));
                preference.setSecondPreference(rs.getString("SecondPreference"));
                preference.setThirdPreference(rs.getString("ThirdPreference"));
                preferences.add(preference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preferences;
    }

    @Override
    public List<Professor> getAvailableProfessors() {
        String sql = "SELECT  p.ProfessorID, p.Name, p.Email, p.Phone, p.StuNumber " +
                     "FROM Professor p " +
                     "LEFT JOIN zhiyuan z ON p.ProfessorID IN (z.FirstPreference, z.SecondPreference, z.ThirdPreference) " +
                     "WHERE z.PreferenceID IS NULL AND p.StuNumber > 0;";

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
                professor.setStuNumber(rs.getInt("StuNumber"));
                professors.add(professor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return professors;
    }
}