<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr th td{
	borer : 1px solid black;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function createForm(formName, actionName, method) {
	var f = $("#fixForm");
	var gbCode = $("<input />");
	var chName = $("<input />");
	var gbTitle = $("<input />");
	var gbReplyContent = $("<input />")
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	gbCode.attr("type","hidden");
	chName.attr("type", "hidden");
	gbTitle.attr("type", "hidden");
	gbReplyContent.attr("type", "hidden");
	gbCode.attr("name","gbCode");
	chName.attr("name", "chName");
	gbTitle.attr("name", "gbTitle");
	gbReplyContent.attr("name", "gbReplyContent");
	gbCode.attr("value",'${gbCode}');
	chName.attr("value",'${writer}');
	gbTitle.attr("value",'${title}');
	gbReplyContent.attr("value",$("input[name=gbReplyContent]").val());
	$("#fixForm").append(gbCode);
	$("#fixForm").append(chName);
	$("#fixForm").append(gbTitle);
	$("#fixForm").append(gbReplyContent);
	
	f.submit();
}
function replyForm(formName, actionName, method, grCode){
	alert(grCode);
	var f = $("#fixForm");
	var grcode = $("<input />");
	var gbCode = $("<input />");
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	grcode.attr("type","hidden");
	gbCode.attr("type","hidden");
	grcode.attr("name","grCode");
	gbCode.attr("name","gbCode");
	grcode.attr("value",grCode);
	gbCode.attr("value",'${gbCode}');
	$("#fixForm").append(grcode);
	$("#fixForm").append(gbCode);
	
	f.submit();
}
</script>
</head>
<body>
<h1>readGBoardPage 입니다.</h1>
<table style='border:1px solid black;'>
	<tr>
		<th>
			작성자 : ${writer }
		</th>
		<td>
			작성일 : ${wdate }
		</td>
		<td>
			조회수 : ${hit }
		</td>
	</tr>
	<tr>
		<th colspan="3"  style='border:1px solid black;'>
			${title }
		</th>
	</tr>
	<tr>
		<td colspan="3">
			${content }
		</td>
	</tr>
</table>
<div id='ajax_div'>${reply }</div>
<div id='replyWrite'>
		${chName } : <input type='text' placeholder='댓글을 달아주세요.' name='gbReplyContent' size='100px;'/>
		<input type='button' onClick='createForm("replygboard","ReplyGBoard","POST")' value='댓글 등록' />
</div>

<a href='GuildBoardPage'>전체 목록</a>
<input type='button' onClick='createForm("deletegboard", "DeleteGBoard", "POST")' value='삭제' />
<input type='button' onClick='createForm("modifygboardpage", "ModifyGBoardPage", "POST")' value='수정' />
<input type='button' onClick='createForm("writegboardpage", "WriteGBoardPage", "GET")' value='글쓰기' />
<form id="fixForm"></form>
</body>
</html>