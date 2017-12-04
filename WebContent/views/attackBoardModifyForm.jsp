<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Modify Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css" href="css/attackBoardModifyForm.css" />
<script>
	function attackBoardModify() {
		attackBoardModifyForm.submit();
	}

	function back() {
		attackBoardContentsForm.submit();
	}

	//폼 생성
	function createForm(formName, action, method) {
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
	}
</script>
<script>
	function init() {
		var userType = "${userType}";
		if (userType == 2) {
			$("#nomar_user").css("display", "none");
			$("#admin_user").css("display", "");
		} else {
			$("#nomar_user").css("display", "");
			$("#admin_user").css("display", "none");
		}
	}
</script>
<style>
</style>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<form name="attackBoardModifyForm" action="AttackBoardModify" method="post">
			<h1>공략게시판</h1>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="attackBoardTitle" value="${attackBoardTitle }" class="titleText" /></th>
				</tr>
				<tr>
					<td><textarea class="text2" cols="110" rows="28" name="attackBoardContents">${attackBoardContents }</textarea></td>
				</tr>
			</table>
			<input type="hidden" name="attackBoardCode" value="${attackBoardCode }" /> 
			<input type="hidden" name="attackBoardUserId" value="${attackBoardUserId }" /> 
			<input type="hidden" name="attackBoardHit" value="${attackBoardHit }" />
			<div class="buttonDiv1">
				<button onClick="attackBoardModify()" class="button">수정완료</button>
			</div>
		</form>
		
		<form name="attackBoardContentsForm" action="AttackBoardContentsView" method="post">
			<input type="hidden" name="attackBoardCode" value="${attackBoardCode }" />
			<input type="hidden" name="attackBoardUserId" value="${attackBoardUserId }" />
			<input type="hidden" name="attackBoardHit" value="${attackBoardHit }" />
			
		</form>
	</div>
</body>
</html>