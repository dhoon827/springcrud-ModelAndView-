<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
 	function logout(){
 		location.href="memberlogout"
 	}
</script>
</head>
<body>
<h1>main.jsp</h1>
<h4>
${sessionScope.loginId}님 환영합니다:)
</h4>
<c:if test="${sessionScope.loginId eq 'admin'}">
	<a href="memberlist">회원목록 조회</a>
</c:if>
<button onclick="location.href='memberupdate?mid=${sessionScope.loginId}'">수정</button>
<button onclick="logout()">로그아웃</button>

</body>
</html>