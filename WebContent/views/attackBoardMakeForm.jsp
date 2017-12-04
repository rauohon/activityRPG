<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Make Form</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css" href="css/attackBoardMakeForm.css"/>
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
	function attackBoardMake(){
		AttackBoardMakeForm.submit();
	}
	function back(){
		createForm("attackBoardForm", "AttackBoard", "post");
		var form = document.getElementsByName("attackBoardForm")[0];
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
</script>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<form name="AttackBoardMakeForm" action="AttackBoardMake" method="post">
		<div>
			<h1>공략게시판</h1>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="attackBoardTitle" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea class="text2" cols="110" rows="28" name="attackBoardContents"></textarea></td>
				</tr>
			</table>
			<div id="buttonDiv">
				<input type="button" onClick="attackBoardMake()" value="작성" class="button"/>
				<input type="reset" value="초기화" class="button" />
				<input type="button" onClick="back()" value="목록" class="button"/>
			</div>
		</div>
		</form>
	</div>
	</body>
</html>