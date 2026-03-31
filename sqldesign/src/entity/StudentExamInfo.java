package entity;

import java.util.Date;

public class StudentExamInfo {
    private int studentId;
    private String name;
    private String gender;
    private Date birthdate;
    private String idNumber;
    private String hometown;
    private String major;
    private String email;
    private String phone;
    private String school;
    private String schoolType;
    private String resume;
    private String luquState;
	private double chushiScore;
    private double fushiScore;
    
    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getLuquState() {
		return luquState;
	}

	public void setLuquState(String luquState) {
		this.luquState = luquState;
	}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getChushiScore() {
        return chushiScore;
    }

    public void setChushiScore(double chushiScore) {
        this.chushiScore = chushiScore;
    }

    public double getFushiScore() {
        return fushiScore;
    }

    public void setFushiScore(double fushiScore) {
        this.fushiScore = fushiScore;
    }
}
