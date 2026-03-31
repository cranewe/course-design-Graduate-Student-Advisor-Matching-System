package com.entity;

import java.util.Arrays;

public class Student {
    // id
    private int id;
    // 姓名
    private String name;
    // 数组 p，大小为3，类型为 String
    private String[] p = new String[3];
    // professor name
    private String[] proName = new String[3];
    // 复试成绩，类型为 double
    private double fstScore;
    // 初试成绩，类型为 double
    private double secScore;
    // 学校姓名，类型为 String
    private String schoolName;
    // 专业
    private String major;
    // 一级学科
    private String fstSubject;
    // 二级学科
    private String secSubject;
    // 表示学生现在处于哪一轮次
    private int round;

    // 构造方法
    public Student(int id, String name, String[] p, double fstScore, double secScore, String schoolName, String major, String fstSubject, String secSubject) {
        this.id = id;
        this.name = name;
        // 确保 p 数组长度为 3
        if (p != null && p.length == 3) {
            this.p = p.clone(); // 避免直接赋值带来的外部引用问题
        } else {
            throw new IllegalArgumentException("Array p must have exactly 3 elements");
        }
        this.fstScore = fstScore;
        this.secScore = secScore;
        this.schoolName = schoolName;
        this.major = major;
        this.fstSubject = fstSubject;
        this.secSubject = secSubject;
        this.round = 1; // 默认初始化为第1轮
    }

    // Getter 和 Setter 方法
    public String[] getProName() {
    	return this.proName;
    }
    
    public void setProName(String[] proName) {
    	this.proName = proName;
    }
    
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
        return p.clone(); // 返回克隆避免外部修改内部数组
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

    // toString 方法，用于打印学生信息
    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", p=" + Arrays.toString(p) +
                ", pro=" + Arrays.toString(proName) +
                ", fstScore=" + fstScore +
                ", secScore=" + secScore +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", fstSubject='" + fstSubject + '\'' +
                ", round=" + round +
                '}';
    }
}
