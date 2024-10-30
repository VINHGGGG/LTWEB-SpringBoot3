<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
</head>
<body>
	<form action="/LTWEB/forget" method="post">
	<h3> Nhập email xác nhận đi anh! </h3>
		<c:if test="${alert != null}">
			<h3 class="alert alert-danger">${alert}</h3>
		</c:if>

		<div class="container">
			<label for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email">

			<button type="submit">Lấy lại mật khẩu giùm em</button>
		</div>
	</form>
</body>
</html>