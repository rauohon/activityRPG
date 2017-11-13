<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News Board Contents</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css" href="/css/newsBoardContents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function init(){
		var userType = "${userType}";
		if(userType==2){
			var modify = document.getElementsByName("newsBoardModifyFormMove")[0];
			modify.setAttribute("type", "button");
			
			var newsdelete = document.getElementsByName("newsBoardDelete")[0];
			newsdelete.setAttribute("type", "button");
			 
			$("#nomar_user").css("display","none");
			$("#admin_user").css("display","");
		}else{
			$("#nomar_user").css("display","");
			$("#admin_user").css("display","none");
		}
	}
	
	function newsBoardModifyFormMove(newsBoardCode){
		createForm("newsBoardModifyFormMoveForm", "NewsBoardModifyFormMove", "post");
	
		var form = document.getElementsByName("newsBoardModifyFormMoveForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "newsBoardCode";
		code.value = newsBoardCode;
		
		form.appendChild(code);
		form.submit();
	}
	function newsBoardDelete(newsBoardCode){
		createForm("newsBoardDeleteForm", "NewsBoardDelete", "post");
		var form = document.getElementsByName("newsBoardDeleteForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "newsBoardCode";
		code.value = newsBoardCode;
		
		form.appendChild(code);
		form.submit();
	}
	function back(){
		createForm("newsBoardForm", "NewsBoard", "post");
		var form = document.getElementsByName("newsBoardForm")[0];
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
	<div id="contentsDiv">
		${newsBoardContentsView }
	</div>
	<div id="buttonDiv">
		<input type="hidden" name="newsBoardModifyFormMove" value="수정" onClick="newsBoardModifyFormMove(${newsBoardCode})" class="button"/>
		<input type="hidden" name="newsBoardDelete" value="삭제" onClick="newsBoardDelete(${newsBoardCode})" class="button"/>
		<button onClick="back()" class="button">목록</button>
	</div>
	</div>
</body>
</html>