<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/svg.js/2.6.3/svg.js"></script>
<script src="js/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	function sendData() {
		//폼 생성 요청
		var form = createForm("pwdFindForm", "PwdFind", "post");

		//현재 페이지명 저장 hidden개체 생성
		createinput("hidden", "id", "${id}");
		
		//생성 된 폼과 요소 개체간의 연결
		relationObj("pwdFindForm", "id");
		//서버 전송
		form.submit();
	}
</script>
<script>
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
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>	
<link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />
	<!-- login -->
	<div class="container" style="padding-top:60px">
		<div id="login">
			<form action="javascript:void(0);" method="get">
				<fieldset class="clearfix">
				
					<!-- 아이디 입력(패스워드 찾기) -->
					<p>
						<span class="fontawesome-user"></span>
						<input type="text" name="id" value="Id" onBlur="if(this.value == '') this.value = 'Id'"
								onFocus="if(this.value == 'Id') this.value = ''" required>
					</p>
					<input type="hidden" value="${id }" name="id" />
					<!-- 로그인 버튼 -->
					<p> <input type="button" id="total" value="ID CHECK" onClick="sendData()"> </p> 
					
					<!-- 아이디/패스워드 찾기, 회원가입  -->
					<p style="padding-left: 200px;">
						<a href="/">메인 페이지</a>
					</p>
					
					<div id="message">${message }</div>
				</fieldset>
			</form>
		</div>
	</div>
	<!-- end login -->
</body>
</html>