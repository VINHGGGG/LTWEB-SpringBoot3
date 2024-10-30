<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
</head>
<body>
	<b>Fullname:</b> ${fullname}
	<br>
	<b>Phone:</b> ${phone}
	<br>
	<b>Images:</b> ${images}
	<form action="/LTWEB/MultipartServlet" enctype="multipart/form-data" method="post">
	<input type="submit" value="Upload Image" />
		<a href="/LTWEB/home">HOME</a>
		<a href="/LTWEB/update">CẬP NHẬT THÔNG TIN</a>
	</form>
</body>