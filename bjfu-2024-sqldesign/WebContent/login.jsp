<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px 30px;
            border-radius: 8px;
            text-align: center;
            width: 300px;
        }
        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .login-container div {
            margin-bottom: 15px;
        }
        .login-container label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            text-align: left;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-container input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .login-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>教师登录</h2>
        <form action="login" method="post">
            <div>
                <label for="professorId">教师ID：</label>
                <input type="text" id="professorId" name="professorId" required>
            </div>
            <div>
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <input type="submit" value="登录">
            </div>
            <%
            // 显示错误消息
            String errorMsg = (String)request.getAttribute("errorMsg");
            if (errorMsg != null) {
            %>
                <div class="error">
                    <%= errorMsg %>
                </div>
            <% } %>
        </form>
    </div>
</body>
</html>
