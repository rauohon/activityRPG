<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/backGround.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function QuestMove(){
			createForm("questAll","QuestAll","post");
			var QuestMoveForm = document.getElementsByName("questAll")[0];
			
			QuestMoveForm.submit();
	}
	function createForm(formName, action, method){
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
		}
	
</script>
<body>
<div>
	${massage }
</div>
<div>
	<input type = "button" value = "퀘스트 목록으로 이동" onClick="QuestMove()" />
</div>
</body>
</html>