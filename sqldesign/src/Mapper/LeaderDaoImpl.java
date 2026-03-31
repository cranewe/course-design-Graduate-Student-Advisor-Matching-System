package Mapper;

import dao.LeaderDao;
import entity.*;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaderDaoImpl implements LeaderDao {

    @Override
    public List<StudentExamInfo> getAllAdmittedStudents() {
    	String sql = "SELECT s.StudentID AS studentId, " +
                "       s.Name AS name, " +
                "       s.Email AS email, " +
                "       s.Phone AS phone, " +
                "       s.School AS school, " +
                "       s.SchoolType AS schoolType, " +
                "       s.Major AS Major, " + 
                "       ISNULL(SUM(cs.Score), 0) AS chushiScore, " +
                "       ISNULL(fs.TotalScore, 0) AS fushiScore " +
                "FROM Student s " +
                "LEFT JOIN chushiScores cs ON s.StudentID = cs.StudentID " +
                "LEFT JOIN fushiScores fs ON s.StudentID = fs.StudentID " +
                "WHERE s.luqustate = '拟录取' " +
                "GROUP BY s.StudentID, s.Name, s.Email, s.Phone, s.School, s.SchoolType, s.Major,fs.TotalScore;";
        List<StudentExamInfo> students = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StudentExamInfo student = new StudentExamInfo();
                student.setStudentId(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setEmail(rs.getString("Email"));
                student.setPhone(rs.getString("Phone"));
                student.setSchool(rs.getString("School"));
                student.setSchoolType(rs.getString("SchoolType"));
                student.setMajor(rs.getString("Major"));
                student.setChushiScore(rs.getDouble("chushiScore"));
                student.setFushiScore(rs.getDouble("fushiScore"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<StudentPreference> getStudentsByAdvisorPreferences(int professorId) {
        String sql = "SELECT DISTINCT " +
                     "    CASE " +
                     "        WHEN z.FirstPreference = ? THEN '第一志愿' " +
                     "        WHEN z.SecondPreference = ? THEN '第二志愿' " +
                     "        WHEN z.ThirdPreference = ? THEN '第三志愿' " +
                     "    END AS Preference, " +
                     "    z.PreferenceID AS StudentID " +
                     "FROM zhiyuan z " +
                     "WHERE z.FirstPreference = ? " +
                     "   OR z.SecondPreference = ? " +
                     "   OR z.ThirdPreference = ?;";

        List<StudentPreference> students = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 设置参数
            stmt.setInt(1, professorId); // FirstPreference
            stmt.setInt(2, professorId); // SecondPreference
            stmt.setInt(3, professorId); // ThirdPreference
            stmt.setInt(4, professorId); // FirstPreference
            stmt.setInt(5, professorId); // SecondPreference
            stmt.setInt(6, professorId); // ThirdPreference

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentPreference student = new StudentPreference();
                    student.setStudentId(rs.getInt("StudentID"));
                    student.setPreference(rs.getString("Preference"));
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }


    @Override
    public List<StudentAssignment> getAllAdvisorAssignments() {
    	String sql = "SELECT DISTINCT ls.StudentID AS studentId, " +
                "       s.Name AS studentName, " +
                "       ls.ProfessorID AS professorId, " +
                "       p.Name AS professorName, " +
                "       ls.SubjectID AS subjectId, " +
                "       sub.Name AS subjectName, " +
                "       ls.luqu_year AS luquYear " +
                "FROM luqu_State ls " +
                "LEFT JOIN Student s ON ls.StudentID = s.StudentID " +
                "LEFT JOIN Professor p ON ls.ProfessorID = p.ProfessorID " +
                "LEFT JOIN Subject sub ON ls.SubjectID = sub.SubjectID;";
        List<StudentAssignment> assignments = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StudentAssignment assignment = new StudentAssignment();
                assignment.setStudentId(rs.getInt("StudentID"));
                assignment.setStudentName(rs.getString("StudentName"));
                assignment.setProfessorID(rs.getInt("ProfessorID"));
                assignment.setProfessorName(rs.getString("professorName"));
                assignment.setSubjectID(rs.getInt("SubjectID"));
                assignment.setSubjectName(rs.getString("subjectName"));
                assignment.setLuquyear(rs.getDate("luquYear"));
                assignments.add(assignment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
