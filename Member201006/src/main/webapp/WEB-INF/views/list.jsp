<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>memberlist.jsp</h1>
<table border="1">
	<tr>
		<th>아이디</th> <th>비밀번호</th> <th>이름</th>
		<th>전화번호</th> <th>이메일</th> <th>생년월일</th>
		<th>상세조회</th> <th>삭제</th></tr>
	<c:forEach var="member" items="${memberList}">
		<tr>
			<td>${member.mid}</td>
			<td>${member.mpassword}</td>
			<td>${member.mname}</td>
			<td>${member.mphone}</td>
			<td>${member.memail}</td>
			<td>${member.mbirth}</td>
			<td><a href="memberview?mid=${member.mid}">조회</a></td>
			<td><a href="memberdelete?mid=${member.mid}">삭제</a></td>
			
	</c:forEach>
	</table>
</body>
</html>