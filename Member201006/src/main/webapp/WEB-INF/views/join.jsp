<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
</head>
<body>
<form action="memberjoin" method="post">
<h1>SIGN UP</h1>
		아이디<br> <input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" maxlength="20" size="50px">
		<p></p>
		비밀번호<br> <input type="text" name="mpassword" id="mpassword" maxlength="20" placeholder="비밀번호를 입력하세요" size="50px"><br>
		<p></p>
		이름<br> <input type="text" name="mname" id="mname" size="50px"><br>
		<p></p>
		이메일<br> <input type="text" name="memail" id="memail" size="50px"><br>
		<p></p>
		휴대전화<br> <input type="text" id="mphone" name="mphone" placeholder="휴대폰 번호를 입력하세요" size="50px" ><br>
		<p></p>
		생년월일<br> <input type="date" name="mbirth" id="mbirth" size="50px"><br>
		<p></p>
		
		<input type="submit" value="회원가입">
		
</form>
</body>
</html>