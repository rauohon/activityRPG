<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/backGround.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>s
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function guildOut(){	
		createForm("guildOutForm", "GuildOut", "post");
		var form = document.getElementsByName("guildOutForm")[0];
		
		form.submit();
	}
	
	
	//폼 생성
	function createForm(formName, action, method){
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
	}
	function guildList(){
		createForm("guild", "Guild", "post");
		var form = document.getElementsByName("guild")[0];
		
		form.submit();
	}
	
</script>
<style>
	h1{
		margin-left:130px;
		font-style:oblique;
		font-size:30px;
	}
	.guildList table{
		margin-left:20px;
		text-align:center;
		
	}
	.guildList th{
		padding:5px 20px;
		font-size:30px;
	}
	.guildList td{
		font-size:20px;
		margin-left:20px;
		padding:10px 0px;
		color:#3D3D3D;
		font-weight:bold;
	}

	body{
		background-image:url("/images/guild.jpg");
		background-repeat:no-repeat;
		background-size:100% 100%;
		overflow:hidden;
		/* background-attachment:fixed; */
	}
	.guildMemberBox{
		background-image:url("/images/guild2.jpg");
		background-repeat:no-repeat;
		background-size:50% 100%;
		overflow:hidden;
		width:1200px;
		height:700px;

		
	}
	.guildButton{
		margin-top:400px;
		margin-left:330px;
	}
	.guildbutton{
		background:linear-gradient(to right, #FFC19E, #FFE5C2);
		border: 1px solid #ccc;
		font-size:23px;
		color: #333333;
	}
</style>
<body>
	<div class="guildMemberBox">
	<h1> ${guildName}의 현재 길드원</h1>
	<div class="guildList">
	${guildMember }
	</div>
	<div class="guildButton">
		<button	class="guildbutton" onClick="guildOut()">길드 탈퇴</button>
		<button class="guildbutton" onClick="guildList()">길드 리스트</button>
	</div>
	</div>
</body>
</html>