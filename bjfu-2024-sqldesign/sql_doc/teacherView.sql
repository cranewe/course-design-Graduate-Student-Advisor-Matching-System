IF OBJECT_ID('ProfessorSubjectView', 'V') IS NOT NULL
    DROP VIEW ProfessorSubjectView;
GO

CREATE VIEW ProfessorSubjectView AS
SELECT 
    p.ProfessorID AS id, 
	childSubject.SubjectID AS SubID,
    p.Name AS name, 
    p.StuNumber AS leftNum, 
    parentSubject.Name AS fstSubject,
	childSubject.Name AS secSubject
FROM 
    Professor p
JOIN 
    SubjectInfo AS childSubject
ON 
    childSubject.SubjectID = p.SubjectID
LEFT JOIN 
    SubjectInfo AS parentSubject
ON 
    parentSubject.SubjectID = childSubject.ParentID;