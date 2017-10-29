<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
function createForm(code) {
	var f = $("#fixForm");
	var i = $("<input />");
	f.attr("name","readgboardpage");
	f.attr("action","ReadGBoardPage");
	f.attr("method","POST");
	i.attr("type","hidden");
	i.attr("name","gbCode");
	i.attr("value",code);
	$("#fixForm").append(i);
	
  document.readgboardpage.submit();
}
</script>
</head>
<body>
<h1>guildBoardPage 입니다.</h1>
${boards }
<a href='WriteGBoardPage'>글 작성</a>
<form id="fixForm"></form>
</body>
</html>