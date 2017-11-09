<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Make Form</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/attackBoardMakeForm.css"/>
<script>
	function attackBoardMake(){
		AttackBoardMakeForm.submit();
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
</style>
<body>
	<form name="AttackBoardMakeForm" action="AttackBoardMake" method="post">
		<div>
		<h1>공략게시판</h1>
			<table class="type">
				<tr>
					<th>제목 : <input type="text" name="attackBoardTitle" class="titleText"/></th>
				</tr>
				<tr>
					<td><textarea class="text2" cols="110" rows="28" name="attackBoardContents"></textarea></td>
				</tr>
			</table>
			<div id="buttonDiv">
				<input type="button" onClick="attackBoardMake()" value="작성" class="button"/>
				<input type="reset" value="초기화" class="button" />
				<input type="button" onClick="back()" value="목록" class="button"/>
			</div>
		</div>
	</form>
</body>
</html>