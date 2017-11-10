<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/backGround.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>

var myquestCode = null;
	function questcode(questCode){
	myquestCode = questCode;
	}

	function Add(myQuestCode){
	
	createForm("questAdd","QuestAdd","post");
	var questAddForm = document.getElementsByName("questAdd")[0];
	
	var questMyCode = document.createElement("input");
	
	questMyCode.type = "hidden";
	questMyCode.name = "myquestCode";
	questMyCode.value = myQuestCode
	
	questAddForm.appendChild(questMyCode)
	questAddForm.submit();
	}

	//폼 생성
	function createForm(formName, action, method){
	var form = document.createElement("form");
	form.name = formName;
	form.action = action;
	form.method = method;
	document.body.appendChild(form);
	}
	
	function myQuestMove(){
		createForm("myQuestList", "MyQuestList", "post");
		var myQuestMoveForm = document.getElementsByName("myQuestList")[0];
		myQuestMoveForm.submit();
	}
</script>
<style>

	.quest1{
		width:600px;
		height:560px;
		background-image:url("/images/questAllButton.jpg");
		background-size:100% 100%;
	}

	h1{
		font-size:40px;
		margin-left:140px;
		margin-top:130px;
	}

	.questTable{
		margin-top:10px;
		font-size:26px;
	}
	.questTable td{
		padding:5px 30px;
	}
	.questAllButton{
		margin-top:50px;
		margin-left:250px;
		width:140px;
	}
	.messageBox{
		color:white;
		margin-left:220px;
	}
	.buttonBox{
		background: linear-gradient(to right, #578100, #B1DB4E);
	}
	.questButton{
		background: linear-gradient(to right, #578100, #B1DB4E);
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
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<style>
		body{
		background-image:url("/images/quest.jpg");
		background-repeat:no-repeat;
		background-size:100% 100%;
		overflow:hidden;
		/* background-attachment:fixed; */
	}
	</style>
	<div id='wraper' style="padding-top: 60px;">	
	<div class="quest1">
		<div style='color: white;'>
			<h1>전체 퀘스트 목록</h1>
		</div>
		<div style="margin-top: 6%;">
			${questList }
		</div>
		<div class="questAllButton">
			<input class="buttonBox" type = "button" value="내 퀘스트창으로 이동" onClick="myQuestMove()"/>
		</div>
		<div class="messageBox">
		${message }
		</div>
	</div>
	</div>
</body>
</html>