<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" type="text/css" href="css/backGround.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var myQuestSuccess = null;
var SuccessCheck = null;
//퀘스트 진행중 확인 , 완료버튼 보상넘김
	function QuestChecking(Code , success){
		if(success==1){
			createForm("questPresent","QuestPresent","post");
			var questSuccessForm = document.getElementsByName("questPresent")[0];
			
			var myquestSuccess = document.createElement("input");
			
			myquestSuccess.type = "hidden";
			myquestSuccess.name = "myquestCode";
			myquestSuccess.value = Code;
			
			questSuccessForm.appendChild(myquestSuccess)
			questSuccessForm.submit();
		
		}else{
		}
		
		
	}
	function createForm(formName, action, method){
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
		}
	function questAll(){
		createForm("questAllMove","QuestAll","post")
		
		var form = document.getElementsByName("questAllMove")[0];
		form.submit();
	}

	
</script>
<style>
	.quest1{
		width:800px;
		height:400px;
		background-image:url("images/questAllButton.jpg");
		background-size:100% 100%;
	}

	h1{
		font-size:50px;
		margin-left:80px;
		margin-top:150px;
		color:silver;
	}
	.questTable{
		margin-top:20px;
		font-size:22px;
		color:white;
	}
	.questTable td{
		padding:5px 20px;
	}
	.questButton{
		margin-left:290px;
		width:140px;
	}
	.buttons{
		background: linear-gradient(to right, #2c3338, #475660);
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
	<style>	
	body{
		background-image:url("images/quest.jpg");
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
	<div>
	<h1>퀘스트 목록</h1>
	</div>
	<div class="quest1">
	<div>
		${myQuestlist }
	</div>
	<div class="questButton">
		<input class="buttons" type="button" value="퀘스트 받기 창으로 이동" onClick="questAll()" />
	</div>
	</div>
	</div>
</body>
</html>