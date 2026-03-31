<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登录</title>
</head>
<body>

<p align="center">
<H1><font color="black">请登录</font></H1>

<form method="get" action="selection">

<input type="hidden" name="StuList" value="1">
	<p align="center">
	ID：
	<input type="text" name="id">

	<p align="center">
	SubID：
	<input type="text" name="SubId">
	
    <input type="hidden" name="id" value="1">
    <input type="hidden" name="SubId" value="2">

	<p align="center">
	<input type="submit" value="登录">   
	</p>

</form>

</body>
</html>