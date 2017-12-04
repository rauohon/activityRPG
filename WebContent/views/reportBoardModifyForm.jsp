<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/reportBoard.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>Report Board Modify Form</title>
</head>
<script>
	function reportBoardModify(){
		reportBoardModifyForm.submit();
	}
	
	function back(){
		reportBoardContentsForm.submit();
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
	.box4{
		width:1150px;
		height:800px;
	}
	
	.buttonOption{
		margin-left:900px;
	}
	.button{
		width:70px;
		height:25px;
		background-color:#CDCDCD;
		color:#1312FF;
		border:2px solid #369;
		border-radius:5px;
	}
	.subjectBox1{
		width:1150px;
		font-size:30px;
		font-color:white;
	}
	.contents{
		width:1150px;
		height:660px;
		font-size:20px;
	}
	h1{
		font-size:18px;
		color:white;
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
	<div id='wraper' style="padding-top: 60px;">
	<form name="reportBoardModifyForm" action="ReportBoardModify" method="post">
		<div class="box4">
			<input type="hidden" name="reportBoardCode" value="${reportBoardCode }" /> 
			<h1>제목</h1> <input class="subjectBox1" type="text" name="reportBoardTitle" value="${reportBoardTitle }" /> <br/>
			<h1>내용</h1> <textarea class="contents" cols="50" rows="20" name="reportBoardContents" >${reportBoardContents }</textarea> 
			<input type="hidden" name="reportBoardUserId" value="${reportBoardUserId }" /> 
			<input type="hidden" name="reportBoardHit" value="${reportBoardHit }" />
		</div>
		<button class="button" onClick="reportBoardModify()">수정완료</button>
	</form>
	<form name="reportBoardContentsForm" action="ReportBoardContentsView" method="post">
		<input type="hidden" name="reportBoardCode" value="${reportBoardCode }" /> 
		<input type="hidden" name="reportBoardHit" value="${reportBoardHit }" />
		<div class="buttonOption">
		<!-- <button class="button" onClick="reportBoardModify()">수정완료</button> -->
		<button class="button" onClick="back()">뒤로가기</button>
		</div>
	</form>
	</div>
</body>
</html>