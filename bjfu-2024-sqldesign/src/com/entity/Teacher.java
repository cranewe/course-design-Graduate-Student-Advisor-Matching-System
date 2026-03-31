package com.entity;

import java.util.List;

public class Teacher {
    private int id;
    private int subId;
    private String name;
    private String fstSubject;
    private String secSubject;
    private int leftNum;
    private List<Student> stuList;
    private int state;	// 处在第几轮
    /**
     * state = 0, 选择完毕
     * state = 1, 第一志愿
     * state = 2
     * state = 3
     * state = 4
     * */
    
    public Teacher(int _id, int subId, String _name, String _fstSubject, String secSubject, int _leftNum) {
        this.id = _id;
        this.subId = subId;
        this.name = _name;
        this.fstSubject = _fstSubject;
        this.secSubject = secSubject;
        this.leftNum = _leftNum;
        this.state = 0;
        this.stuList = null;
    }

    // Getter and Setter for id
    public int getState() {
    	return this.state;
    }
    
    public void setState(int state) {
    	this.state = state;
    }
    
    public int getSubjectId() {
        return this.subId;
    }
    public void setSubjectId(int c_id) {
        this.subId = c_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for fstSubject
    public String getFstSubject() {
        return fstSubject;
    }

    public void setFstSubject(String fstSubject) {
        this.fstSubject = fstSubject;
    }
    
    public String getSecSubject() {
        return this.secSubject;
    }

    // Getter and Setter for leftNum
    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    // Getter and Setter for stuList
    public List<Student> getStuList() {
        return stuList;
    }

    public void setStuList(List<Student> stuList) {
        this.stuList = stuList;
    }

    // toString method for printing the object's information
    @Override
    public String toString() {
        return "Teacher {" +
                "id=" + id +
                ", subId=" + subId +
                ", name='" + name + '\'' +
                ", fstSubject='" + fstSubject + '\'' +
                ", secSubject='" + secSubject + '\'' +
                ", leftNum=" + leftNum +
                ", stuList=" + (stuList != null ? stuList : "No students assigned") +
                '}';
    }
}
