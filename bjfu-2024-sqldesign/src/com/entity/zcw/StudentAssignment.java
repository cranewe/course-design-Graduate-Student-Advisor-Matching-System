package com.entity.zcw;

import java.sql.Date;

public class StudentAssignment {
    private int studentId;
    private String StudentName;
    private int professorID;
    private String professorName;
    private int subjectID;
    private String subjectName;
    private Date luquyear;
    
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Date getLuquyear() {
		return luquyear;
	}
	public void setLuquyear(Date luquyear) {
		this.luquyear = luquyear;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getProfessorID() {
		return professorID;
	}
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

    
    
}
