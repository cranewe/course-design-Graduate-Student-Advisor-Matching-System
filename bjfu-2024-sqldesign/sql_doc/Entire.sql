-- 学生实体
IF OBJECT_ID('Student', 'U') IS NOT NULL
    DROP TABLE Student;
GO
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,			-- 学生ID
    Name NVARCHAR(50),					-- 姓名
    Gender NVARCHAR(10),				-- 性别
    BirthDate DATE,						-- 出生日期
    IDNumber NVARCHAR(20) UNIQUE,		-- 身份证号
    Hometown NVARCHAR(50),				-- 生源地
    Major NVARCHAR(50),					-- 本科专业
    Email NVARCHAR(50),					-- 邮箱
    Phone NVARCHAR(20),					-- 电话
    School NVARCHAR(100),				-- 本科学校
    SchoolType NVARCHAR(20),			-- 本科学校类型
    Resume NVARCHAR(MAX),				-- 个人简历
    luqustate NVARCHAR(20)				-- 复试录取状态 
);
GO

-- 导师实体
IF OBJECT_ID('Professor', 'U') IS NOT NULL
    DROP TABLE Professor;
GO
CREATE TABLE Professor (
    ProfessorID INT,					-- 导师ID
    Name NVARCHAR(50),					-- 姓名
    Title NVARCHAR(50),					-- 职称
    Photopath NVARCHAR(MAX),			-- 照片路径
    Biography NVARCHAR(MAX),			-- 简介
    Email NVARCHAR(50),					-- 邮箱
    Phone NVARCHAR(20),					-- 电话
    SubjectID INT,						-- 学科ID
    StuNumber INT,						-- 招生资格
	PRIMARY KEY (ProfessorID, SubjectID) 
);
GO

-- 学科实体
IF OBJECT_ID('SubjectInfo', 'U') IS NOT NULL
    DROP TABLE Subject;
GO
CREATE TABLE SubjectInfo (
    SubjectID INT PRIMARY KEY,			-- 学科ID
    Name NVARCHAR(50),					-- 学科名称
    Level NVARCHAR(20),					-- 学科等级
    ParentID INT,						-- 上级学科
    Dscrpt NVARCHAR(MAX),				-- 学科概述
    Type NVARCHAR(20),					-- 学科类型
);
GO

-- 招生目录
IF OBJECT_ID('zhaoshengmulu', 'U') IS NOT NULL
    DROP TABLE zhaoshengmulu;
GO
CREATE TABLE zhaoshengmulu(
    CatalogID INT PRIMARY KEY,			-- 招生目录ID
    SubjectID INT,						-- 学科ID
    ProfessorID INT,					-- 导师ID
    chushiSubjects NVARCHAR(200),		-- 初试科目
    fushiSubjects NVARCHAR(200),		-- 复试科目
    AnnualNum INT,						-- 学科年度指标
    ExtraNum INT,						-- 学科年度补充指标
    Year INT							-- 年度
);
GO

-- 初试成绩
IF OBJECT_ID('chushiScores', 'U') IS NOT NULL
    DROP TABLE chushiScores;
GO
CREATE TABLE chushiScores (
    StudentID INT,
    CourseName NVARCHAR(50),
    Score DECIMAL(5, 2)
);
GO

-- 复试成绩
IF OBJECT_ID('fushiScores', 'U') IS NOT NULL
    DROP TABLE fushiScores;
GO
CREATE TABLE fushiScores (
    StudentID INT,
    Comment NVARCHAR(200),
    LanScore DECIMAL(5, 2),
    MajorScore DECIMAL(5, 2),
    OtherScore DECIMAL(5, 2),
    TotalScore DECIMAL(5, 2)
);
GO

-- 复试登记表
IF OBJECT_ID('fushiInfo', 'U') IS NOT NULL
    DROP TABLE fushiInfo;
GO
CREATE TABLE fushiInfo (
    StudentID INT PRIMARY KEY,
    TargetMajor NVARCHAR(50),
    StuType NVARCHAR(20),
    biyeDate DATE,
    urgenContact NVARCHAR(50),
    fushiTime DATETIME,
    fushiLoc NVARCHAR(100),
    tiaoji NVARCHAR(20),				-- 调剂
    fangxiang NVARCHAR(50)				-- 研究方向
);
GO

-- 志愿顺序
IF OBJECT_ID('zhiyuan', 'U') IS NOT NULL
    DROP TABLE zhiyuan;
GO
CREATE TABLE zhiyuan (
    PreferenceID INT PRIMARY KEY,
    FirstPreference NVARCHAR(50),
    SecondPreference NVARCHAR(50),
    ThirdPreference NVARCHAR(50)
);
GO

-- 学生和学科的关联表
IF OBJECT_ID('StudentSubject', 'U') IS NOT NULL
    DROP TABLE StudentSubject;
GO
CREATE TABLE StudentSubject (
    StudentID INT,
    SubjectID INT
);
GO

-- 学生和复试登记表的关联表
IF OBJECT_ID('StudentRetest', 'U') IS NOT NULL
    DROP TABLE StudentRetest;
GO
CREATE TABLE StudentRetest (
    StudentID INT,
    RetestID INT
);
GO

IF OBJECT_ID('luquState', 'U') IS NOT NULL
	DROP TABLE luquState;
GO
CREATE TABLE luquState(
	StudentId int,
	ProfessorId int,
	SubjectId int,
	luqu_year Date
);
GO

IF OBJECT_ID('Users', 'U') IS NOT NULL
	DROP TABLE luquState;
GO
CREATE TABLE Users (
    zhanghao INT PRIMARY KEY,       -- 账号，主键
    password NVARCHAR(50) NOT NULL  -- 密码，长度50，不允许为空
);
GO