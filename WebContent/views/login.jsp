<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/svg.js/2.6.3/svg.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
function init(){
	var userType = "${userType}";
	if(userType==2){
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
}
</script>

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
	<!-- login -->
		<link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />
	<div class="container" style="padding-top: 60px">
		<div id="login">

			<fieldset class="clearfix">
				<form action="Access" method="post">
					<!-- JS because of IE support; better: placeholder="Username" -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="id" value="Username" value="${id }"
							onBlur="if(this.value == '') this.value = 'Username'"
							onFocus="if(this.value == 'Username') this.value = ''" required>
					</p>
					<!-- JS because of IE support; better: placeholder="Password" -->
					<p>
						<span class="fontawesome-lock"></span> 
						<input type="password" name="pwd" value="Password" 
							onBlur="if(this.value == '') this.value = 'Password'"
							onFocus="if(this.value == 'Password') this.value = ''" required>
					</p>
					<!-- 로그인 버튼 -->
					<!-- <p> <input type="button" value="Log In" onClick="sendData()"> </p>  -->
					<input type="submit" id="total" value="Log In" />
				</form>
				<!-- 아이디/패스워드 찾기, 회원가입  -->
				<p>
					<a href="IdFind">아이디 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="PwdFind">패스워드 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="JoinForm">회원가입</a>
				</p>
				<div id="message">${message }</div>
			</fieldset>
		</div>
	</div>
	<!-- end login -->
</body>
</html>