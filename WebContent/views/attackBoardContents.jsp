<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Contents</title>
</head>
<link rel="stylesheet" type="text/css"
	href="/css/attackBoardContents.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script>
	function init(){
		var userType = "${userType}";
		if(userType==""){
			
		}else{
			var modify = document.getElementsByName("attackBoardModifyFormMove")[0];
			modify.setAttribute("type", "button");
			
			var attackdelete = document.getElementsByName("attackBoardDelete")[0];
			attackdelete.setAttribute("type", "button");
		}
	}
	
	function attackBoardModifyFormMove(attackBoardCode){
		createForm("attackBoardModifyFormMoveForm", "AttackBoardModifyFormMove", "post");
	
		var form = document.getElementsByName("attackBoardModifyFormMoveForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "attackBoardCode";
		code.value = attackBoardCode;
		
		form.appendChild(code);
		form.submit();
	}
	function attackBoardDelete(attackBoardCode){
		createForm("attackBoardDeleteForm", "AttackBoardDelete", "post");
		var form = document.getElementsByName("attackBoardDeleteForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "attackBoardCode";
		code.value = attackBoardCode;
		
		form.appendChild(code);
		form.submit();
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
<style>
</style>
<body onload="init()">
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<div id="contentsDiv">${attackBoardContentsView }</div>
		<div id="buttonDiv">
			<input type="hidden" name="attackBoardModifyFormMove" value="수정"
				onClick="attackBoardModifyFormMove(${attackBoardCode})"
				class="button" /> <input type="hidden" name="attackBoardDelete"
				value="삭제" onClick="attackBoardDelete(${attackBoardCode})"
				class="button" />
			<button onClick="back()" class="button">목록</button>
		</div>
	</div>
</body>
</html>