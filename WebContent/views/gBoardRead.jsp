<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function createForm(formName, actionName, method) {
	var f = $("#fixForm");
	var title = $("<input />");
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	title.attr("type","hidden");
	title.attr("name","gbCode");
	title.attr("value",'${gbCode}');
	$("#fixForm").append(title);
	
	f.submit();
}
</script>
</head>
<body>
<h1>readGBoardPage 입니다.</h1>

<h1>${writer }</h1>
<h1>${title }</h1>
<h1>${content }</h1>
<h1>${wdate }</h1>
<h1>${hit }</h1>

<a href='GuildBoardPage'>전체 목록</a>
<input type='button' onClick='createForm("replygboardpage", "ReplyGBoardPage", "GET")' value='답글 달기' />
<input type='button' onClick='createForm("deletegboard", "DeleteGBoard", "GET")' value='삭제' />
<input type='button' onClick='createForm("modifygboardpage", "ModifyGBoardPage", "GET")' value='수정' />
<input type='button' onClick='createForm("writegboardpage", "WriteGBoardPage", "GET")' value='글쓰기' />
<form id="fixForm"></form>
</body>
</html>