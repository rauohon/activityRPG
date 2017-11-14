<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Board Contents</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css"
	href="/css/questionBoardContents.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function init(){
	var userType = ${userType};
	 if(userType != ""){
		 if("${id}" == "${qbMbid}"){
		var modify = document.getElementsByName("questionBoardModifyFormMove")[0];
		modify.setAttribute("type", "button");
		
		var questiondelete = document.getElementsByName("questionBoardDelete")[0];
		questiondelete.setAttribute("type", "button");
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
	
	function questionBoardModifyFormMove(qbCode){
		createForm("questionBoardModifyFormMoveForm", "QuestionBoardModifyFormMove", "post");
	
		var form = document.getElementsByName("questionBoardModifyFormMoveForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "qbCode";
		code.value = qbCode;
		
		form.appendChild(code);
		form.submit();
	}
	function questionBoardDelete(qbCode){
		createForm("questionBoardDeleteForm", "QuestionBoardDelete", "post");
		var form = document.getElementsByName("questionBoardDeleteForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "qbCode";
		code.value = qbCode;
		
		form.appendChild(code);
		form.submit();
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
</script>
<style>
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
	<div id="contentsDiv">${questionBoardContentsView }</div>
	<div id="buttonDiv">
		<input type="hidden" name="questionBoardModifyFormMove" value="수정"
			onClick="questionBoardModifyFormMove(${qbCode})" class="button" /> <input
			type="hidden" name="questionBoardDelete" value="삭제"
			onClick="questionBoardDelete(${qbCode})" class="button" />
		<button onClick="back()" class="button">목록</button>
	</div>
	</div>
</body>
</html>