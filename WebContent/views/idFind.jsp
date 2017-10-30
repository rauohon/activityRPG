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
	function idFind() {
		//폼 생성 요청
		var form = createForm("idFindForm", "IdFind", "post");

		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "page", "home", "");

		//생성 된 폼과 요소 개체간의 연결
		relationObj("idFindForm", "phone");

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
				
					<!-- 이메일 입력(아이디찾기) -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="phone" value="Phone" onBlur="if(this.value == '') this.value = 'Phone'"
								onFocus="if(this.value == 'Phone') this.value = ''" required>
					</p>
					
					<!-- 이메일 확인 버튼 -->
					<p> <input type="button" value="PHONE CHECK" onClick="idFind()"> </p> 
					
					<!-- 아이디/패스워드 찾기, 회원가입  -->
					<p style="padding-left: 90px;">
						<a href="PwdFind">패스워드 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/">메인 페이지</a>
					</p>
					
					<br/>${message }
				</fieldset>
			</form>
		</div>
	</div>
	<!-- end login -->
</body>
</html>