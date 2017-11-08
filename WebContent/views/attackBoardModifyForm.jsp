<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Modify Form</title>
</head>
<script>
	function attackBoardModify(){
		attackBoardModifyForm.submit();
	}
	
	function back(){
		attackBoardContentsForm.submit();
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
<body>
	<form name="attackBoardModifyForm" action="AttackBoardModify" method="post">
		<div>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="attackBoardTitle" value="${attackBoardTitle }" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea cols="110" rows="28" name="attackBoardContents" >${attackBoardContents }</textarea></td>
				</tr>
			</table>
			<input type="hidden" name="attackBoardCode" value="${attackBoardCode }" /> 
			<input type="hidden" name="attackBoardUserId" value="${attackBoardUserId }" /> 
			<input type="hidden" name="attackBoardHit" value="${attackBoardHit }" />
		</div>
		<div class="buttonDiv1">
			<button onClick="attackBoardModify()" class="button">수정완료</button>
		</div>
		
	</form>
	<form name="attackBoardContentsForm" action="AttackBoardContentsView" method="post">
		<input type="hidden" name="attackBoardCode" value="${attackBoardCode }" /> 
		<input type="hidden" name="attackBoardHit" value="${attackBoardHit }" />
		<div class="buttonDiv2">
			<button onClick="back()" class="button">뒤로가기</button>
		</div>
	</form>
</body>
</html>