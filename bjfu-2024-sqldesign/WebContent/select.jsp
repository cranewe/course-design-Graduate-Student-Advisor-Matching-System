<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Teacher" %>
<%@ page import="com.entity.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>老师信息与学生选择</title>
    <script>
        function validateSelection(maxSelections) {
            const checkboxes = document.querySelectorAll('input[name="selectedStudents"]:checked');
            if (checkboxes.length > maxSelections) {
                alert("选择的学生数量不能超过剩余的招生名额！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>老师信息</h1>
    <div>
        <%-- 从后端获取传递的教师对象 --%>
        <% Teacher teacher = (Teacher) request.getAttribute("teacher"); %>
        <% if (teacher != null) { %>
            <p>教师姓名: <%= teacher.getName() %></p>
            <p>当前方向: <%= teacher.getSecSubject() %></p>
            <p>剩余招生名额：<%= teacher.getLeftNum() %></p>
            
            <h2>学生列表</h2>
            <form action="selection" method="post" onsubmit="return validateSelection(<%= teacher.getLeftNum() %>);">
                <input type="hidden" name="id" value="<%= teacher.getId() %>">
                <input type="hidden" name="SubId" value="<%= teacher.getSubjectId() %>">
                <table border="1">
                    <thead>
                        <tr>
                            <th>选择：</th>
                            <th>学生姓名：</th>
                            <th>学生本科专业：</th>
                            <th>学生硕士目标专业：</th>
                            <th>学生初试成绩：</th>
                            <th>学生复试成绩：</th>
                            <th>学生第一志愿：</th>
                            <th>学生第二志愿：</th>
                            <th>学生第三志愿：</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Student stu : teacher.getStuList()) { %>
                            <tr>
                                <td><input type="checkbox" name="selectedStudents" value="<%= stu.getId() %>"></td>
                                <td><%= stu.getName() %></td>
                                <td><%= stu.getMajor() %></td>
                                <td><%= stu.getSecSubject() %></td>
                                <td><%= stu.getFstScore() %></td>
                                <td><%= stu.getSecScore() %></td>
                                <td><%= stu.getProName()[0] %>导师</td>
                                <td><%= stu.getProName()[1] %>导师</td>
                                <td><%= stu.getProName()[2] %>导师</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                <br>
                <button type="submit">提交选择</button>
            </form>
        <% } else { %>
            <p>未获取到教师信息。</p>
        <% } %>
    </div>
</body>
</html>




