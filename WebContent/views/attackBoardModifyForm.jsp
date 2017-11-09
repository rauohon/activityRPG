<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Modify Form</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/attackBoardModifyForm.css"/>
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
</style>
<body>
	<form name="attackBoardModifyForm" action="AttackBoardModify" method="post">
		<div>
		<h1>공략게시판</h1>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="attackBoardTitle" value="${attackBoardTitle }" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea class="text2" cols="110" rows="28" name="attackBoardContents" >${attackBoardContents }</textarea></td>
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