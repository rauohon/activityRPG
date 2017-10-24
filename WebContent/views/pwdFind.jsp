<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/svg.js/2.6.3/svg.js"></script>
<script src="js/common.js"></script>
<link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />

<script>
	function sendData() {
		//폼 생성 요청
		var form = createForm("pwdFindForm", "PwdFind", "post");

		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "page", "home", "");
		alert("hiddenForm");

		//생성 된 폼과 요소 개체간의 연결
		relationObj("pwdFindForm", "id");
		alert("relatopmObj");

		//서버 전송
		form.submit();
	}
</script>
</head>

<body>
	<!-- login -->
	<div class="container">
		<div id="login">
			<form action="javascript:void(0);" method="get">
				<fieldset class="clearfix">
				
					<!-- 아이디 입력(패스워드 찾기) -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="pwd" value="Password" onBlur="if(this.value == '') this.value = 'Pwd'"
								onFocus="if(this.value == 'Pwd') this.value = ''" required>
					</p>
					
					<!-- 로그인 버튼 -->
					<p> <input type="button" value="ID CHECK" onClick="sendData()"> </p> 
					
					<!-- 아이디/패스워드 찾기, 회원가입  -->
					<p style="padding-left: 200px;">
						<a href="/">메인 페이지</a>
					</p>
					
					<h4>${message }</h4>
				</fieldset>
			</form>
		</div>
	</div>
	<!-- end login -->
</body>
</html>