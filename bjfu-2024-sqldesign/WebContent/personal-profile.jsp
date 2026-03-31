<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.entity.Professor" %>
<%@ page import="com.entity.ProfessorSubject" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人简介</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 2px solid #f1f1f1;
            padding-bottom: 15px;
        }
        .profile-photo {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 30px;
        }
        .profile-info {
            flex-grow: 1;
        }
        .profile-info h1 {
            margin: 0 0 10px 0;
            color: #333;
        }
        .profile-info p {
            margin: 5px 0;
            color: #666;
        }
        .subjects-section {
            margin-top: 20px;
        }
        .subjects-table {
            width: 100%;
            border-collapse: collapse;
        }
        .subjects-table th, .subjects-table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        .subjects-table th {
            background-color: #f8f8f8;
        }
        .subjects-table tr:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
        .edit-form {
            margin-top: 20px;
            border-top: 2px solid #f1f1f1;
            padding-top: 20px;
        }
        .edit-form div {
            margin-bottom: 15px;
        }
        .edit-form label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .edit-form input, 
        .edit-form textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .submit-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%
        Professor professor = (Professor) request.getAttribute("professor");
        List<ProfessorSubject> subjects = (List<ProfessorSubject>) request.getAttribute("subjects");
        String success = request.getParameter("success");
    %>

    <div class="container">
        <% if (professor != null) { %>
            <div class="profile-header">
                <img src="<%= professor.getPhotoPath() %>" alt="教师照片" class="profile-photo">
                <div class="profile-info">
                    <h1><%= professor.getName() %></h1>
                    <p><strong>教师ID：</strong> <%= professor.getProfessorId() %></p>
                    <p><strong>职称：</strong> <%= professor.getTitle() %></p>
                    <p><strong>邮箱：</strong> <%= professor.getEmail() %></p>
                    <p><strong>电话：</strong> <%= professor.getPhone() %></p>
                </div>
            </div>

            <div class="profile-info">
                <h2>个人简介</h2>
                <p><%= professor.getBiography() %></p>
            </div>

            <div class="subjects-section">
                <h2>学科信息</h2>
                <table class="subjects-table">
                    <thead>
                        <tr>
                            <th>学科名称</th>
                            <th>学科等级</th>
                            <th>学科类型</th>
                            <th>招生指标</th>
                        </tr>
                    </thead>
                   		<tbody><% for(ProfessorSubject subject : subjects) { %>
        				<tr onclick="handleSubjectClick('<%= subject.getSubjectId() %>', '<%= professor.getProfessorId() %>')">
            			<td><%= subject.getSubjectName() %></td>
            			<td><%= subject.getSubjectLevel() %></td>
            			<td><%= subject.getSubjectType() %></td>
            			<td><%= subject.getEnrollmentQuota() %></td>
        </tr>
    <% } %>
</tbody>

                </table>
            </div>

            <div class="edit-form">
                <h2>修改个人信息</h2>
                <% if ("true".equals(success)) { %>
                    <p style="color: green;">信息更新成功！</p>
                <% } else if ("false".equals(success)) { %>
                    <p style="color: red;">信息更新失败，请重试。</p>
                <% } %>

                <form action="personal-profile" method="post">
                    <div>
                        <label>照片路径：</label>
                        <input type="text" name="photoPath" value="<%= professor.getPhotoPath() %>">
                    </div>
                    <div>
                        <label>个人简介：</label>
                        <textarea name="biography" rows="4"><%= professor.getBiography() %></textarea>
                    </div>
                    <div>
                        <label>邮箱：</label>
                        <input type="email" name="email" value="<%= professor.getEmail() %>">
                    </div>
                    <div>
                        <label>电话：</label>
                        <input type="text" name="phone" value="<%= professor.getPhone() %>">
                    </div>
                    <div>
                        <button type="submit" class="submit-btn">更新信息</button>
                    </div>
                </form>
            </div>
        <% } else { %>
            <p>未找到教师信息</p>
        <% } %>
    </div>
    
	<script>
    function handleSubjectClick(subjectId, professorId) {
        // 跳转到 next.jsp，带上 subjectId 和 professorId 参数
        const url = `next.jsp?subjectId=${subjectId}&professorId=${professorId}`;
        window.location.href = url;
    }
</script>
	


</body>
</html>