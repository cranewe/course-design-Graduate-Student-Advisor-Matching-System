IF OBJECT_ID('stu_info', 'V') IS NOT NULL
    DROP VIEW stu_info;
GO

CREATE VIEW stu_info AS
SELECT 
    s.StudentID AS id,
    s.Name AS name,
    z.FirstPreference AS p1,
    z.SecondPreference AS p2,
    z.ThirdPreference AS p3,
    SUM(c.Score) AS fstScore,
    f.TotalScore AS secScore,
    s.School AS schoolName,
    s.Major AS major,
	s.luqustate AS luquState,
    parentSubject.Name AS fstSubject,
	childSubject.Name AS secSubject
FROM student s
JOIN zhiyuan z ON z.PreferenceID = s.StudentID
JOIN chushiScores c ON c.StudentID = s.StudentID
JOIN fushiScores f ON f.StudentID = s.StudentID
JOIN StudentSubject ss ON ss.StudentID = s.StudentID
JOIN SubjectInfo AS childSubject ON childSubject.SubjectID = ss.SubjectID
LEFT JOIN SubjectInfo AS parentSubject ON parentSubject.SubjectID = childSubject.ParentID
GROUP BY 
    s.StudentID, s.Name, z.FirstPreference, z.SecondPreference, z.ThirdPreference, 
    f.TotalScore, s.School, s.Major, s.luqustate, childSubject.Name, parentSubject.Name;