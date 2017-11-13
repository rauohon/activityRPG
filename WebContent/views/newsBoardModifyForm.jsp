<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News Board Modify Form</title>
</head>
<script>
	function newsBoardModify(){
		newsBoardModifyForm.submit();
	}
	
	function back(){
		newsBoardContentsForm.submit();
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
	table.type {
		background: no-repeat fixed;
    	width: 750px;
    	height: 500px;
    	border: 1px solid #444444;
    	margin : 20px 10px;
    	background: url("/images/notice.png") no-repeat center center;
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
	.buttonDiv1{
		margin-left: 600px;
	}
	.buttonDiv2{
		margin-left: 680px;
		margin-top: -25.5px;
	}
	.button{
		width:70px;
		height:25px;
		background-color:#2c3338;
		color:white;
		border:1px solid #369;
		border-radius:5px;
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
<div id='wraper' style="padding-top: 60px;">
	<form name="newsBoardModifyForm" action="NewsBoardModify" method="post">
		<div>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="newsBoardTitle" value="${newsBoardTitle }" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea cols="110" rows="28" name="newsBoardContents" >${newsBoardContents }</textarea></td>
				</tr>
			</table>
			<input type="hidden" name="newsBoardCode" value="${newsBoardCode }" /> 
			<input type="hidden" name="newsBoardUserId" value="${newsBoardUserId }" /> 
			<input type="hidden" name="newsBoardHit" value="${newsBoardHit }" />
		</div>
		<div class="buttonDiv1">
			<button onClick="newsBoardModify()" class="button">수정완료</button>
		</div>
	</form>
	<form name="newsBoardContentsForm" action="NewsBoardContentsView" method="post">
		<input type="hidden" name="newsBoardCode" value="${newsBoardCode }" /> 
		<input type="hidden" name="newsBoardHit" value="${newsBoardHit }" />
		<div class="buttonDiv2">
			<button onClick="back()" class="button">뒤로가기</button>
		</div>
	</form>
	</div>
</body>
</html>