<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>HomePage</title>
<script src="js/common.js"></script>
<script>
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
<%@ include file="nav.jsp"%>
<div id='wraper' style="padding-top:60px;">
	<table id="get">
		<tr>
			<td><a href="LoginForm">로그인</a></td>
			<td><a href="JoinForm">회원가입</a></td>
			<td><a href="IdFind">아이디 찾기</a></td>
			<td><a href="PwdFind">패스워드 찾기</a></td>
		</tr>
	</table>
	<a href='GuildBoardPage'>길드 게시판 이동</a>
		<table id="get">
			<tr>
				<td><a href="LoginForm">로그인</a></td>
				<td><a href="JoinForm">회원가입</a></td>
				<td><a href="IdFind">아이디 찾기</a></td>
				<td><a href="PwdFind">패스워드 찾기</a></td>
			</tr>
		</table>
	
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
</div>
</body>
</html>