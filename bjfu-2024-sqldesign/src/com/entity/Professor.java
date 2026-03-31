package com.entity;

import java.io.Serializable;

public class Professor implements Serializable {
    // ���л��汾��
    private static final long serialVersionUID = 1L;

    // ���Զ���
    private int professorId;
    private String name;
    private String title;
    private String photoPath;
    private String biography;
    private String email;
    private String phone;
    private int subjectId;
    private int stuNumber;

    // �޲ι��췽��
    public Professor() {}

    // ȫ�������췽��
    public Professor(int professorId, String name, String title, String photoPath, 
                     String biography, String email, String phone, 
                     int subjectId, int stuNumber) {
        this.professorId = professorId;
        this.name = name;
        this.title = title;
        this.photoPath = photoPath;
        this.biography = biography;
        this.email = email;
        this.phone = phone;
        this.subjectId = subjectId;
        this.stuNumber = stuNumber;
    }

    // Getter �� Setter ����
    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(int stuNumber) {
        this.stuNumber = stuNumber;
    }


    // toString ���������ڴ�ӡ������Ϣ
    @Override
    public String toString() {
        return "Professor{" +
                "professorId=" + professorId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", subjectId=" + subjectId +
                '}';
    }

    // equals ���������ڱȽ�����Professor����
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professor professor = (Professor) o;

        return professorId == professor.professorId;
    }

    // hashCode ���������equals����ʹ��
    @Override
    public int hashCode() {
        return professorId;
    }
}