<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>update.jsp</h1>
<form action="memberupdateprocess" method="post" name="updateform">
		아이디<br>
		<input type="text" name="mid" id="mid" value="${memberUpdate.mid}" readonly><br>
		비밀번호<br>
		<input type="password" name="mpassword" id="mpassword" value="${memberUpdate.mid}"><br>
		이름<br>
		<input type="text" name="mname" id="mname" value="${memberUpdate.mname}"><br>
		전화번호<br>
		<input type="text" name="mphone" id="mphone" value="${memberUpdate.mphone}"><br>
		이메일<br>
		<input type="text" name="memail" id="memail" value="${memberUpdate.memail}"><br>
		생년월일<br>
		<input type="date" name="mbirth" id="mbirth" value="${memberUpdate.mbirth}"><br>
		<input type="submit" value="수정하기">
	</form>
</body>
</html>