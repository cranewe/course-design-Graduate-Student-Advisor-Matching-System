package com.entity;

public class ProfessorSubject {
    private String subjectName;
    private String subjectLevel;
    private String subjectType;
    private int enrollmentQuota;
    private int subjectId;

    public ProfessorSubject() {}

    public ProfessorSubject(String subjectName, String subjectLevel, 
                             String subjectType, int enrollmentQuota ,int subjectId) {
        this.subjectName = subjectName;
        this.subjectLevel = subjectLevel;
        this.subjectType = subjectType;
        this.enrollmentQuota = enrollmentQuota;
        this.subjectId = subjectId;
    }

    // Getters and Setters
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSubjectLevel() { return subjectLevel; }
    public void setSubjectLevel(String subjectLevel) { this.subjectLevel = subjectLevel; }

    public String getSubjectType() { return subjectType; }
    public void setSubjectType(String subjectType) { this.subjectType = subjectType; }

    public int getEnrollmentQuota() { return enrollmentQuota; }
    public void setEnrollmentQuota(int enrollmentQuota) { this.enrollmentQuota = enrollmentQuota; }
    
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}