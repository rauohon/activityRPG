<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeBoardUpdate</title>
<script src="js/common.js"></script>
<script>
	function freeSuccess(){
		var form = createForm("freeUpageForm", "freeUpdateCheck", "post");
		createinput("hidden", "code", "${code}");
		createinput("hidden", "id", "${id}");
		createinput("hidden", "content", "${content}");
		createinput("hidden", "title", "${title}");
		
		relationObj("freeUpageForm", "code");
		relationObj("freeUpageForm", "id");
		relationObj("freeUpageForm", "content");
		relationObj("freeUpageForm", "title");
		form.submit();
	}
</script>
</head>
<body>
	<input type="hidden" name="id" value="${id }"/>
	<input type="hidden" name="code" value="${code }"/>
	<h4>자유게시판 글 수정</h4>
	<p>제목 : <input type="text" name="title" value="${title }" /></p>
	
	<p>내용</p>
	<input type="text" name="content" value="${content }" />
	<p>날짜 : ${date }</p>
	<button onClick="freeSuccess()">수정 등록</button>
</body>
</html>