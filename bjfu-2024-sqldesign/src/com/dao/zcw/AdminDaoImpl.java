package com.dao.zcw;

import com.dao.zcw.AdminDao;
import com.entity.zcw.*;
import com.util.zcw.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public int publishAdmissionResults() {
        String sql = "UPDATE Student SET luqustate = '已发布' WHERE luqustate = '拟录取'";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Student> getStudentsWithMissingInfo() {
        String sql = "SELECT * FROM Student WHERE Name IS NULL OR Email IS NULL OR Phone IS NULL";
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
    public List<StudentExamInfo> getAllExamInfo() {
        String sql = "SELECT s.StudentID AS studentId, " +
                "       s.Name AS name, " +
                "       s.Email AS email, " +
                "       s.Phone AS phone, " +
                "       ISNULL(SUM(cs.Score), 0) AS chushiScore, " +
                "       ISNULL(fs.TotalScore, 0) AS fushiScore " +
                "FROM Student s " +
                "LEFT JOIN chushiScores cs ON s.StudentID = cs.StudentID " +
                "LEFT JOIN fushiScores fs ON s.StudentID = fs.StudentID " +
                "GROUP BY s.StudentID, s.Name, s.Email, s.Phone, fs.TotalScore";
        List<StudentExamInfo> examInfos = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StudentExamInfo examInfo = new StudentExamInfo();
                examInfo.setStudentId(rs.getInt("studentId"));
                examInfo.setName(rs.getString("name"));
                examInfo.setEmail(rs.getString("email"));
                examInfo.setPhone(rs.getString("phone"));
                examInfo.setChushiScore(rs.getDouble("chushiScore"));
                examInfo.setFushiScore(rs.getDouble("fushiScore"));
                examInfos.add(examInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examInfos;
    }
}
