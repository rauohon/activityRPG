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
<script src="js/common.js"></script>
<script>
	function total(formname, action, method){
		var form = createForm(formname, action, method);
		
		createObj("hidden", "id", "${id}", "");
		
		relationObj("mainForm", "id");
		relationObj("msgForm", "id");
		relationObj("freeBoardForm", "id");
		relationObj("gameForm", "id");
		relationObj("infoForm", "id");
		relationObj("accessForm", "id");
		form.submit();
	}
 	function init() {
		var logincheck = document.getElementById("logincheck");
		var get = document.getElementById("get");
		var info = document.getElementById("info");
		var logout = document.getElementById("logout");
		var state = "${id }";

		if (state != "") {
			logincheck.style.display = "block";
			get.style.display = "none";
			info.style.display = "block";
			logout.style.display = "block";
		} else {
			logincheck.style.display = "none";
			get.style.display = "block";
			info.style.display = "none";
			logout.style.display = "none";
		}
	}
</script>
</head>
<body onLoad="init()">

	<div id="layer_fixed">
		<table id="table" cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td id="maintext">TEXT-RPG</td>
			</tr> -->
			<tr>
				<td><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></td>
				<td><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">BOARD</button></td>
				<td><button onClick="total('gameForm', 'GameForm', 'post')" id="bar">GAME PLAY</button></td>
			</tr>
		</table>
	</div>
	<br/><br/><br/>
		<table id="get">
			<tr>
				<td><a href="LoginForm">로그인</a></td>
				<td><a href="JoinForm">회원가입</a></td>
				<td><a href="IdFind">아이디 찾기</a></td>
				<td><a href="PwdFind">패스워드 찾기</a></td>
			</tr>
		</table>
	<a href="LoginForm">login Go</a>
	<a href="JoinForm">회원가입</a>
	<button onClick="info()">나의 정보</button>
	<form action="AccessOut" method="post">
	<input type="submit" value="로그아웃" />
	</form>
	<a href='GuildBoardPage'>길드 게시판 이동</a>
	
	<h2 id="logincheck">${id }님 로그인 된 메인 페이지 입니다.</h2>
	<table id="nodab">
		<tr>
		<td><button onClick="total('infoForm', 'Info', 'post')" id="info">나의 정보</button></td>
		<td><button onClick="total('accessForm', 'AccessOut', 'post')" id="logout">로그아웃</button></td>
		</tr>
	</table>
	<!-- 나의 정보쪽에서 확인 -->
<!-- <div id="game">
		<a href='ActivityDayLogPage'>운동정보 확인(회원)</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='EnrollRaspberryPiPage'>라즈베리파이 등록</a>
	 </div> -->
</body>
</html>