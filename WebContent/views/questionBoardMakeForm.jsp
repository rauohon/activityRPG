<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Board Make Form</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">   
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<script>
	function questionBoardMake(){
		QuestionBoardMakeForm.submit();
	}
	function back(){
		createForm("questionBoardForm", "QuestionBoard", "post");
		var form = document.getElementsByName("questionBoardForm")[0];
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
	function init(){
			 $("#nomar_user").css("display","");
			 $("#admin_user").css("display","none");
	}
</script>
<style>
	table.type {
		background: no-repeat fixed;
    	width: 750px;
    	height: 500px;
    	border: 1px solid #444444;
    	margin : 20px 10px;
    	background: url("images/question.jpg") no-repeat center center;
    	background-size: 400px 400px;
	}
	table.type th{
		height:19px;
    	padding: 10px;
    	text-align:left;
    	font-weight: bold;
    	vertical-align: top;
    	color: white;
    	border-bottom: 1px solid #ccc;
    	background: linear-gradient(to right, #2c3338, #475660);
	}
	.titleText{
		width: 630px;
	}
	#buttonDiv{
		margin-left: 570px;
	}
	.buttons{
		width:70px;
		height:25px;
		background-color:#2c3338;
		color:white;
		border:1px solid #369;
		border-radius:5px;
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
<body onload="init()">
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">		
	<h1><a href='BackPage' class='button'>뒤로가기</a></h1>
	<form name="QuestionBoardMakeForm" action="QuestionBoardMake" method="post">
		<div>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="qbTitle" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea cols="110" rows="28" name="qbContent"></textarea></td>
				</tr>
			</table>
			<div id="buttonDiv">
				<input type="button" onClick="questionBoardMake()" value="작성" class="buttons"/>
				<input type="reset" value="초기화" class="buttons" />
				<input type="button" onClick="back()" value="목록" class="buttons"/>
			</div>
		</div>
	</form>
	</div>
</body>
</html>