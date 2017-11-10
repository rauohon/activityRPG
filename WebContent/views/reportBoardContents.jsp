<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/reportBoard.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Report Board Contents</title>
</head>
<script>
	function init(){
		var userType = "${userType}";
		if(userType != ""){
			if("${userId}" == "${ReportBoardUserId}"){
				var modify = document.getElementsByName("reportBoardModifyFormMove")[0];
				modify.setAttribute("type", "button");
			
				var attackdelete = document.getElementsByName("reportBoardDelete")[0];
				attackdelete.setAttribute("type", "button");
		}else{
			
		}
	}else{
		
	}
}
	
	function reportBoardModifyFormMove(reportBoardCode){
		createForm("reportBoardModifyFormMoveForm", "ReportBoardModifyFormMove", "post");
	
		var form = document.getElementsByName("reportBoardModifyFormMoveForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "reportBoardCode";
		code.value = reportBoardCode;
		
		form.appendChild(code);
		form.submit();
	}
	function reportBoardDelete(reportBoardCode){
		createForm("reportBoardDeleteForm", "ReportBoardDelete", "post");
		var form = document.getElementsByName("reportBoardDeleteForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "reportBoardCode";
		code.value = reportBoardCode;
		
		form.appendChild(code);
		form.submit();
	}
	function back(){
		createForm("reportBoardForm", "ReportBoard", "post");
		var form = document.getElementsByName("reportBoardForm")[0];
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
	.divClass{
		display:none;
	} 
	div#div0{
		display:block;
	}
	.listNum{
		background-color:white;
		outline:none;
	}
	
</style>

<body onload="init()">
	<div id="contentsDiv">
		${reportBoardContentsView }
	</div>
	<div id="buttonDiv">
		<input type="hidden" name="reportBoardModifyFormMove" value="수정" onClick="reportBoardModifyFormMove(${reportBoardCode})" class="button"/>
		<input type="hidden" name="reportBoardDelete" value="삭제" onClick="reportBoardDelete(${reportBoardCode})" class="button"/>
		<button onClick="back()" class="button">뒤로가기</button>
	</div>
</body>
</html>