<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Home.jsp</h1>

	<button onclick="location.href='memberloginform'">로그인</button>
	<button onclick="location.href='memberjoinform'">회원가입</button>
	
	<h3>카카오 회원가입</h3>
	<a href="kakaojoin">
		<img src="${pageContext.request.contextPath}/resources/img/kakao_login_medium_narrow.png">
	</a>

</body>
</html>
