<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/reportBoard.css"/>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>Report Board Make Form</title>
</head>
<script>
	function reportBoardMake(){
		ReportBoardMakeForm.submit();
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
	.contentsText{
		width:200px;
		height:100px;
	}
	.allmake{
		width:1200px;
		height:500px;
		color:white;
	}
	.subjectbox{
		width:1200px;
		height:30px;
		font-size:30px;
	}
	.contentsbox{
		width:1200px;
		height:600px;
		font-size:40px;
	}
	.button{
		background: linear-gradient(to right, #2c3338, #475660);
		color:white;
	}
	.buttonOption{
		margin-left: 900px;
	}
</style>
<script type="text/javascript">
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
	<div id='wraper' style="padding-top: 60px;">
	<form name="ReportBoardMakeForm" action="ReportBoardMake" method="post">
		<div class="allmake">
			제목 : <input class="subjectbox" type="text" name="reportBoardTitle"/><br/>
			내용 : <textarea class="contentsbox" cols="50" rows="20" name="reportBoardContents"></textarea><br/>
			<div class="buttonOption">
			<input class="button" type="file" value="파일 업로드" /><br/>
			<input class="button" type="button" onClick="reportBoardMake()" value="작성" />
			<input class="button" type="reset" value="초기화" />
			<input class="button" type="button" onClick="back()" value="뒤로가기" />
			</div>
		</div>
	</form>
	</div>
</body>
</html>