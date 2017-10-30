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
/* 	function sendData() {
		//폼 생성 요청
		var form = createForm("accessForm", "Access", "post");

		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "page", "home", "");
		alert("hiddenForm");

		//생성 된 폼과 요소 개체간의 연결
		relationObj("accessForm", "id");
		relationObj("accessForm", "pwd");
		relationObj("accessForm", "page");
		alert("relatopmObj");

		//서버 전송
		form.submit();
	} */
</script>
</head>

<body>
	<!-- login -->
	<div class="container">
		<div id="login">
			
				<fieldset class="clearfix">
				<form  action="Access" method="post">
					<!-- JS because of IE support; better: placeholder="Username" -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="id" value="Username" onBlur="if(this.value == '') this.value = 'Username'"
								onFocus="if(this.value == 'Username') this.value = ''" required>
					</p>
					
					<!-- JS because of IE support; better: placeholder="Password" -->
					<p>
						<span class="fontawesome-lock"></span>
						<input type="password" name="pwd" value="Password" onBlur="if(this.value == '') this.value = 'Password'"
								onFocus="if(this.value == 'Password') this.value = ''" required>
					</p>
					
					<!-- 로그인 버튼 -->
					<!-- <p> <input type="button" value="Log In" onClick="sendData()"> </p>  -->
					<input type="submit" value="Log In"/>
				</form>
					<!-- 아이디/패스워드 찾기, 회원가입  -->
					<p>
						<a href="IdFind">아이디 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="PwdFind">패스워드 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="JoinForm">회원가입</a>
					</p>
					
					<h4>${message }</h4>
				</fieldset>
		
		</div>
	</div>
	<!-- end login -->
</body>
</html>