package com.entity.zcw;

public class StudentPreference {
    private int studentId; // 学生ID
    private String studentName; // 学生姓名
    private String preference; // 志愿类别（第一志愿、第二志愿、第三志愿）
    private String FirstPreference;
    private String SecondPreference;
    private String ThirdPreference;

    public StudentPreference() {
    }

    public StudentPreference(int studentId, String studentName, String preference) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.preference = preference;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
    
    

    public String getFirstPreference() {
		return FirstPreference;
	}

	public void setFirstPreference(String firstPreference) {
		FirstPreference = firstPreference;
	}

	public String getSecondPreference() {
		return SecondPreference;
	}

	public void setSecondPreference(String secondPreference) {
		SecondPreference = secondPreference;
	}

	public String getThirdPreference() {
		return ThirdPreference;
	}

	public void setThirdPreference(String thirdPreference) {
		ThirdPreference = thirdPreference;
	}

	@Override
    public String toString() {
        return "StudentPreference{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", preference='" + preference + '\'' +
                '}';
    }
}
