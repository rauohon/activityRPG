<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Contents</title>
</head>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function init(){
		var userType = "${userType}";
		if(userType != ""){
			if("${id}" == "${AttackBoardUserId}"){
				var modify = document.getElementsByName("attackBoardModifyFormMove")[0];
				modify.setAttribute("type", "button");
				
				var attackdelete = document.getElementsByName("attackBoardDelete")[0];
				attackdelete.setAttribute("type", "button");
			}
		}else{
			
		}
		if(userType==2){
			 $("#nomar_user").css("display","none");
			 $("#admin_user").css("display","");
		}else{
			$("#nomar_user").css("display","");
			$("#admin_user").css("display","none");
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
body {
	background-color: #2c3338;
}

table.type {
	background: no-repeat fixed;
	width: 750px;
	height: 500px;
	border: 1px solid white;
	margin: 20px 10px;
	color: white;
}

table.type th {
	height: 19px;
	padding: 10px;
	font-size: 30px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: linear-gradient(to right, #2c3338, #475660);
}

th.code {
	width: 100px;
}

th.title {
	width: 147px;
	text-align: left;
}

table.type td {
	padding: 10px;
}

td.id {
	height: 12px;
	font-size: 11px;
	font-weight: bold;
	border-bottom: 1px dotted #ccc;
}

td.date {
	height: 12px;
	font-size: 11px;
	font-weight: bold;
	text-align: right;
	border-bottom: 1px dotted #ccc;
}

td.hit {
	height: 12px;
	font-size: 11px;
	font-weight: bold;
	text-align: right;
	border-bottom: 1px dotted #ccc;
}

td.contents {
	word-break: break-all;
}

h1 {
	color: white;
}

#buttonDiv {
	margin-left: 570px;
}

.button {
	width: 70px;
	height: 25px;
	background-color: #2c3338;
	color: white;
	border: 1px solid #369;
	border-radius: 5px;
}
</style>
<body onload="init()">
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<div id="contentsDiv">
		${attackBoardContentsView }
	</div>
	<div id="buttonDiv">
		<input type="hidden" name="attackBoardModifyFormMove" value="수정" onClick="attackBoardModifyFormMove(${attackBoardCode})" class="button"/>
		<input type="hidden" name="attackBoardDelete" value="삭제" onClick="attackBoardDelete(${attackBoardCode})" class="button"/>
		<button onClick="back()" class="button">목록</button>
	</div>
	</div>
</body>
</html>