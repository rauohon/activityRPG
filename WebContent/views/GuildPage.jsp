<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/backGround.css"/>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function guildCreateMove(){
		createForm("guildCreateFormMove","GuildCreateFormMove","post");
		var guildCreateMoveForm = document.getElementsByName("guildCreateFormMove")[0];
		guildCreateMoveForm.submit();
	}

	function createForm(formName, action, method){
	var form = document.createElement("form");
	form.name = formName;
	form.action = action;
	form.method = method;
	document.body.appendChild(form);
	}
	function guildJoin(GuildName){
		
		createForm("guildJoinMove","GuildJoinMove","post");
		var guildJoinForm = document.getElementsByName("guildJoinMove")[0];
		
		var guildName = document.createElement("input");
		
		guildName.type = "hidden";
		guildName.name = "guildName";
		guildName.value = GuildName;
		
		guildJoinForm.appendChild(guildName);
		guildJoinForm.submit();
		
	}
	function guildMemberList(Name){
		
		createForm("guildMemberMove","GuildMemberMove","post");
		var guildMemberForm = document.getElementsByName("guildMemberMove")[0];
		
		var guildNameForm = document.createElement("input");
		
		guildNameForm.type = "hidden";
		guildNameForm.name = "guildName";
		guildNameForm.value = Name;
		
		guildMemberForm.appendChild(guildNameForm);
		guildMemberForm.submit();
		
	}
</script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
	.guildView{
		overflow:hidden;
		width:100%;
		margin-top:15%;
		margin-left:15%;
		text-align:center;
	}
	.guildmessage{
		color:white;
		margin-right:464px;
		font-size:30px;
	}
	h1{
		font-family: 'Hanna', serif;
		padding-top:5%;
		padding-bottom:5%;
		font-size:50px;
		color:black;
		margin-right:375px;
	}
	.buttonMassage{
		margin-right:444px;
	}
	.buttonCreate{
		margin-left:169px;
	}
	.guildjoinButton{
		background: linear-gradient(to right, #A6A6A6, #D9E5FF);
	}
	.buttonCreate{
		background: linear-gradient(to right, #A6A6A6, #D9E5FF);
	}
	.guildList{
		font-family: 'Hanna', serif;
		width:100%;
	}
	.guildList tr{
		font-size:33px;
		color:black;
	}
	.guildList td{
		padding-top:2%;
		font-size:30px;
		color:black;
	}
	.guildList th{
		font-size:33px;
		color:black;
		padding: 10px;
	}
	.guildBackground{
		width:70%;
		background-image:url("images/guildList.jpg");
	}
</style>
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
	<div id='wraper' style="padding-top: 60px;">
	<a href='BackPage' class='button'>뒤로가기</a>
		<style>
		body{
			background-image:url("images/guild.jpg");
			background-repeat:no-repeat;
			background-size:100% 100%;
			overflow:hidden;
			/* background-attachment:fixed; */
		}
				.button {
	background: none;
	border: 3px solid #fff;
	border-radius: 5px;
	color: #fff;
	text-transform: uppercase;
}
.button:hover {
	border: 3px solid #f68a6f;
	background: #f68a6f;
}
		</style>
		<div class="guildView" style="padding-right: 0px; padding-left: 0px;">
			<div class="guildBackground">
				<h1>길드</h1>
				${listGuildView }
				<div class="buttonMassage">
					<input class="buttonCreate" type = "button" value = "길드 생성"	 onClick="guildCreateMove()">
				</div>
				<div class="guildmessage">
				${message }
				</div>
			</div>
		</div>
	</div>
</body>
</html>