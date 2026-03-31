package com.dao;
import com.entity.Professor;
import com.entity.ProfessorSubject;
import java.util.List;

public interface ProfessorDAO {
    Professor getProfessorById(int professorId);
    boolean updateProfessorInfo(Professor professor);
    boolean validateLoginFromUsers(int professorId, String password);
    List<Integer> getProfessorIdsByZhanghao(int zhanghao);
    List<ProfessorSubject> getProfessorSubjects(int professorId);
}
