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
	function guildCreate(){
		createForm("guildCreate","GuildCreate","post");
		var createMoveForm = document.getElementsByName("guildCreate")[0];
		
		var Name = document.getElementsByName("guildName")[0];
		
		createMoveForm.appendChild(Name);
		createMoveForm.submit();
	}
	
	//폼 생성
	function createForm(formName, action, method){
	var form = document.createElement("form");
	form.name = formName;
	form.action = action;
	form.method = method;
	document.body.appendChild(form);
	}
	function guildBack(){
		createForm("guild","Guild","post");
		var myRankingForm = document.getElementsByName("guild")[0];
		myRankingForm.submit();
	}
</script>
<style>
	body{
		background-image:url("images/guild.jpg");
		background-repeat:no-repeat;
		background-size:100% 100%;
		overflow:hidden;
		/* background-attachment:fixed; */
	}
	.createButton{
		margin-top:500px;
		margin-left:550px;
	}
	.buttons{
		background: linear-gradient(to right, #A6A6A6, #D9E5FF)
	}
	.textBox{
		
	}
	.messageBox{
		color:white;
	}
	.guildBack{
		background: linear-gradient(to right, #A6A6A6, #D9E5FF);
		width:220px;
	}
</style>
</head>
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
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
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
	<div id='wraper' style="padding-top: 60px;">
		<a href='BackPage' class='button'>뒤로가기</a>
	<div class="createButton">
	<input class="textBox" type ="text" name = "guildName" maxlength=5/>
	<input class="buttons" type ="button" value = "생성" onClick ="guildCreate()" />
	<div class="messageBox">
	${message }
	</div>
	<div>
	<input class="guildBack" type ="button" value = "길드 리스트 보러가기" onClick="guildBack()" />
	</div>
	</div>
</body>
</html>