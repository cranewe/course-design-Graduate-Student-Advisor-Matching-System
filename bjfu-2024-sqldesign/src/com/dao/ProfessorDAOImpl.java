package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.entity.Professor;
import com.entity.ProfessorSubject;

public class ProfessorDAOImpl implements ProfessorDAO {
    private Connection connection;

    public ProfessorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean validateLoginFromUsers(int professorId, String password) {
        String sql = "SELECT * FROM Users WHERE zhanghao = ? AND password = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, professorId);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Professor getProfessorById(int professorId) {
        Professor professor = null;
        String sql = "SELECT TOP 1 * FROM ProfessorDetailsView WHERE ProfessorID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, professorId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    professor = new Professor(
                        rs.getInt("ProfessorID"),
                        rs.getString("ProfessorName"),
                        rs.getString("ProfessorTitle"),
                        rs.getString("Photopath"),
                        rs.getString("Biography"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getInt("SubjectID"),
                        rs.getInt("EnrollmentQuota")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return professor;
    }

    @Override
    public List<ProfessorSubject> getProfessorSubjects(int professorId) {
        List<ProfessorSubject> subjects = new ArrayList<>();
        String sql = "SELECT SubjectID, SubjectName, SubjectLevel, SubjectType, EnrollmentQuota " +
                     "FROM ProfessorDetailsView WHERE ProfessorID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, professorId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ProfessorSubject subject = new ProfessorSubject(
                        
                        rs.getString("SubjectName"),
                        rs.getString("SubjectLevel"),
                        rs.getString("SubjectType"),
                        rs.getInt("EnrollmentQuota"),
                        rs.getInt("SubjectID")
                    );
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return subjects;
    }

    @Override
    public boolean updateProfessorInfo(Professor professor) {
        String sql = "UPDATE Professor SET " +
                     "Photopath = ?, Biography = ?, Email = ?, Phone = ? " +
                     "WHERE ProfessorID IN (SELECT ProfessorID FROM Professor WHERE ProfessorID = ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, professor.getPhotoPath());
            pstmt.setString(2, professor.getBiography());
            pstmt.setString(3, professor.getEmail());
            pstmt.setString(4, professor.getPhone());
            pstmt.setInt(5, professor.getProfessorId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Integer> getProfessorIdsByZhanghao(int zhanghao) {
        List<Integer> professorIds = new ArrayList<>();
        String sql = "SELECT DISTINCT ProfessorID FROM ProfessorDetailsView WHERE ProfessorID IN (SELECT ProfessorID FROM Professor) AND ProfessorID IN (SELECT zhanghao FROM Users WHERE zhanghao = ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, zhanghao);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    professorIds.add(rs.getInt("ProfessorID"));
                }
            }
        } catch (SQLException e) {
    
            e.printStackTrace();
            
        }
        
        return professorIds;
    }
}