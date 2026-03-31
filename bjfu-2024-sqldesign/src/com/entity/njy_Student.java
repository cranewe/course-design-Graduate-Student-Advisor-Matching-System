package com.entity;

import java.util.Arrays;

public class njy_Student {
    // id
    private int id;
    // ����
    private String name;
    // ���� p����СΪ3������Ϊ String
    private String[] p = new String[3];
    // ���Գɼ�������Ϊ double
    private double fstScore;
    // ���Գɼ�������Ϊ double
    private double secScore;
    // ѧУ����������Ϊ String
    private String schoolName;
    // רҵ
    private String major;
    // һ��ѧ��
    private String fstSubject;
    // ����ѧ��
    private String secSubject;
    // ��ʾѧ�����ڴ�����һ�ִ�
    private int round;

    public njy_Student(int id, String name, String[] p, double fstScore, double secScore, String schoolName, String major, String fstSubject, String secSubject) {
        this.id = id;
        this.name = name;
        if (p != null && p.length == 3) {
            this.p = p.clone(); 
        } else {
            throw new IllegalArgumentException("Array p must have exactly 3 elements");
        }
        this.fstScore = fstScore;
        this.secScore = secScore;
        this.schoolName = schoolName;
        this.major = major;
        this.fstSubject = fstSubject;
        this.secSubject = secSubject;
        this.round = 1; // Ĭ�ϳ�ʼ��Ϊ��1��
    }

    // Getter �� Setter ����
    public String getSecSubject() {
    	return  this.secSubject;
    }
    
    public double getFstScore() {
        return fstScore;
    }

    public void setFstScore(double fstScore) {
        this.fstScore = fstScore;
    }

    public double getSecScore() {
        return secScore;
    }

    public void setSecScore(double secScore) {
        this.secScore = secScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getP() {
        return p.clone(); // ���ؿ�¡�����ⲿ�޸��ڲ�����
    }

    public void setP(String[] p) {
        if (p != null && p.length == 3) {
            this.p = p.clone();
        } else {
            throw new IllegalArgumentException("Array p must have exactly 3 elements");
        }
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFstSubject() {
        return fstSubject;
    }

    public void setFstSubject(String fstSubject) {
        this.fstSubject = fstSubject;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    // toString ���������ڴ�ӡѧ����Ϣ
    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", p=" + Arrays.toString(p) +
                ", fstScore=" + fstScore +
                ", secScore=" + secScore +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", fstSubject='" + fstSubject + '\'' +
                ", round=" + round +
                '}';
    }
}