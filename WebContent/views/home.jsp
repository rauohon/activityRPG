<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>HomePage</title>
<script src="/js/common.js"></script>
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
	<div id='wraper' style="padding-top: 60px;">
		<table id="get">
			<tr>
				<td><a href="LoginForm">로그인</a></td>
				<td><a href="JoinForm">회원가입</a></td>
				<td><a href="IdFind">아이디 찾기</a></td>
				<td><a href="PwdFind">패스워드 찾기</a></td>
			</tr>
		</table>
		<h2 id="logincheck">${id }님로그인된 메인 페이지 입니다.</h2>
		<table id="nodab">
			<tr>
				<td><button onClick="total('infoForm', 'Info', 'post')" id="info">나의 정보</button></td>
				<td><button onClick="total('accessForm', 'AccessOut', 'post')" id="logout">로그아웃</button></td>
			</tr>
		</table>
		<br/>
	</div>
	${message } ${message2 }
</body>
</html>