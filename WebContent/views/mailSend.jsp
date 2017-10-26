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
	function emailSend() {
		//폼 생성 요청
		var form = createForm("mailSendForm", "MailSend", "post");

		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "", "home", "");

		//생성 된 폼과 요소 개체간의 연결
		relationObj("mailSendForm", "email");

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
				
					<!-- 이메일 입력(임시비밀번호 보내기) -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="email" value="Email" onBlur="if(this.value == '') this.value = 'Email'"
								onFocus="if(this.value == 'Email') this.value = ''" required>
					</p>
					
					<!-- 이메일 보내기 버튼 -->
					<p> <input type="button" value="EMAIL SEND" onClick="emailSend()"> </p> 
					
					<!-- 아이디/패스워드 찾기, 회원가입  -->
					<p style="padding-left: 90px;">
						<a href="PwdFind">패스워드 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/">메인 페이지</a>
					</p>
					
					</br>${message }
				</fieldset>
			</form>
		</div>
	</div>
	<!-- end login -->
</body>
</html>