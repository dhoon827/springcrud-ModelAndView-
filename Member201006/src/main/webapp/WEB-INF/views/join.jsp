<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function idOverlap(){
		var inputId = document.getElementById("mid").value;
		var idch = document.getElementById("idch");
		//ajax(Asynchronous javascript and XML) 비동기 통신
		//JSON(JavaScript Object Notation)
		$.ajax({
			
			//JSON방식
			type : "post",
			url : "idoverlap",
			data : {"mid" : inputId},
			dataType : "text",
			
			success : function(result){
				if(result=="OK"){
					idch.style.color = "green";
					idch.innerHTML = "사용가능한 ID 입니다.";
					/* alert("사용가능한 ID 입니다."); */
				}else{
					idch.style.color = "red";
					idch.innerHTML = "이미 사용중인 ID 입니다.";
					/* alert("이미 사용중인 ID 입니다."); */
				}
			},
			error : function(){
				idch.style.color = "red";
				idch.innerHTML = "ajax 실패!!";
				/* alert("ajax 실패!!"); */
			}
		
		});
	}
</script>
</head>
<body>
<h1>SIGN UP</h1>
<form action="memberjoin" method="post">
		<c:choose>
			<c:when test="${kakaoId ne null}">
				아이디<br> <input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" maxlength="20" size="50px" onkeyup="idOverlap()">
				<input type="hidden" name="kakaoId" value="${kakaoId}"><br>
				<span id="idch"></span>
			</c:when>
			<c:otherwise>
				아이디<br> <input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" maxlength="20" size="50px" onkeyup="idOverlap()">
				<span id="idch"></span>
			<!-- 	 <input type="button" value="아이디중복확인" onclick="idOverlap()"> -->
			</c:otherwise>
		</c:choose>

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