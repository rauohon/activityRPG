<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board Make Form</title>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
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
table.type {
	background: no-repeat fixed;
	width: 750px;
	height: 500px;
	border: 1px solid #444444;
	margin: 20px 10px;
	background: url("/images/notice.png") no-repeat center center;
}

table.type th {
	height: 19px;
	padding: 10px;
	text-align: left;
	font-weight: bold;
	vertical-align: top;
	color: white;
	border-bottom: 1px solid #ccc;
	background: linear-gradient(to right, #2c3338, #475660);
}

.titleText {
	width: 630px;
}

#buttonDiv {
	margin-left: 570px;
}

.button {
	width: 70px;
	height: 25px;
	background-color: #2c3338;
	color: white;
	border: 1px solid #369;
	border-radius: 5px;
}
</style>
<body>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<form name="AttackBoardMakeForm" action="AttackBoardMake" method="post">
			<div>
				<table class="type">
					<tr>
						<th>제목 : <input type="text" name="attackBoardTitle"
							class="titleText" /></th>
					</tr>
					<tr>
						<td><textarea cols="110" rows="28" name="attackBoardContents"></textarea></td>
					</tr>
				</table>
				<div id="buttonDiv">
					<input type="button" onClick="attackBoardMake()" value="작성"
						class="button" /> <input type="reset" value="초기화" class="button" />
					<input type="button" onClick="back()" value="목록" class="button" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>