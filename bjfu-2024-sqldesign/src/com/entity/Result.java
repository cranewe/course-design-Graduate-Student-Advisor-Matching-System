package com.entity;

import java.sql.Date;

public class Result {
    private String stuID;		// 学生ID
    private String proID;		// 老师ID
    private String subID;		// 二级学科ID
    private Date luquDate;		// 录取年份

    // Constructor
    public Result(String stuID, String proID, String subID, Date luquDate) {
        this.stuID = stuID;
        this.proID = proID;
        this.subID = subID;
        this.luquDate = luquDate;
    }

    // Getters and Setters
    public int getStuID() {
        return Integer.parseInt(this.stuID);
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public int getProID() {
        return Integer.parseInt(proID);
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public int getSubID() {
        return Integer.parseInt(subID);
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public Date getLuquDate() {
        return luquDate;
    }

    public void setLuquDate(Date luquDate) {
        this.luquDate = luquDate;
    }

    // toString Method
    @Override
    public String toString() {
        return "Result{" +
                "stuID='" + stuID + '\'' +
                ", proID='" + proID + '\'' +
                ", subjectName='" + subID + '\'' +
                ", luquDate=" + luquDate +
                '}';
    }
}
