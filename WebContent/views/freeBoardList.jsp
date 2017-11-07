<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 리스트 페이지</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="js/common.js"></script>
<link rel="stylesheet" href="css/freeBoardList.css" media="screen" type="text/css" />
<script>
	function board() {
		var form = createForm('freeBoardInsertForm', 'freeInsertPage', 'post');
		createObj("hidden", "id", "${id}", "");
		relationObj("freeBoardInsertForm", "id");
		form.submit();
	}

	function viewContents(code) {
		alert(code);
		var form = createForm("contentsForm", "freeBoardContent", "post");
		createinput("hidden", "code", code)

		relationObj("contentsForm", "code");
		form.submit();
	}

	function freedelete(code, id) {
		alert(code);
		var form = createForm("freeDeleteForm", "freeBoardDelete", "post");
		createinput("hidden", "code", code)
		createinput("hidden", "id", id);

		relationObj("freeDeleteForm", "code");
		relationObj("freeDeleteForm", "id");
		form.submit();
	}
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
	<div style="padding-top:60px;">
	<button onClick="board()" id="click">게시판 글 쓰기</button>
	${freelist }
	<div>${message }</div>
	</div>
</body>
</html>