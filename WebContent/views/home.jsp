<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<script>
	function info(){
		var form = createForm("infoForm", "Info", "post");

		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "", "home", "");

		//생성 된 폼과 요소 개체간의 연결
		relationObj("infoForm", "id");

		//서버 전송
		form.submit();
	}

/* 	function init() {

		var accessForm = document.getElementById("accessForm");
		var accessOn = document.getElementById("accessOn");

		var state = "${state }"; // 값을 던질떄 문자 0 or 1로준다

		if (state == "1") {

			accessForm.style.display = "none";
			accessOn.style.display = "block";
			window.open("ws://192.168.0.239:8080/test/chat-ws", "window",
					"width=500,height=300");
		} else {
			accessForm.style.display = "block";
			accessOn.style.display = "none";

		}

	} */
</script>
</head>
<<<<<<< HEAD
<body>
	<a href="LoginForm">login Go</a>
	<a href="JoinForm">회원가입</a>
=======
<body onLoad="init()">

	<div id="layer_fixed">
		<table cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td id="maintext">TEXT-RPG</td>
			</tr> -->
			<tr>
				<td><a href="LoginForm">login Go</a></td>
				<td><a href="JoinForm">회원가입</a></td>
				<td><a href="IdFind">아이디 찾기</a></td>
				<td><a href="PwdFind">패스워드 찾기</a></td>
				<td></td>
			</tr>
		</table>
	</div>
	</br></br></br>
	<h4 id="logincheck">${id }님 로그인 된 메인 페이지 입니다.</h4>
	<button onClick="info()">나의 정보</button>
	<form action="AccessOut" method="post">
	<input type="submit" value="로그아웃" />
	</form>
>>>>>>> 57a8f917fba6119a8bce1ca3b313b7397493edbe
	<a href='GameForm'>플레이!!</a>
	<a href='ActivityDayLogPage'>운동정보 확인(회원)</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href='AdminActivityLogPage'>운동정보 확인(관리자)</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href='EnrollRaspberryPiPage'>라즈베리파이 등록</a>
</body>
</html>