<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/backGround.css"/>
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
		background-image:url("/images/guild.jpg");
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
<body>
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