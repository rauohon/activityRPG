<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeInserPage</title>
<link rel="stylesheet" href="css/writingMessage.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
function freeBoardList(){
	var form = createForm("freeListForm", "freeBoardList", "post");
	createinput("hidden", "id", "${id}");
	relationObj("freeListForm", "id");
	form.submit();
}

function insert(){
	var form = createForm("freeListForm", "freeInsert", "post");
	createinput("hidden", "id", "${id}");
	createinput("hidden", "title", "${title}");
	createinput("hidden", "content", "${content}");
	relationObj("freeListForm", "id");
	relationObj("freeListForm", "title");
	relationObj("freeListForm", "content");
	form.submit();
}
</script>
</head>
<body>
	<div id="setMessage">
		<div class="head">
			<h2>자유게시판 글 쓰기</h2>
		</div>
		<div class="container">
			<p>글 쓰는 사람 : ${id }</p>
			<input type="text" id="a" name="title" placeholder="제목" /><br />
			<input type="text" id="b" name="content" placeholder="내용" /> <br />
			<button id="submit" onClick="insert()">게시글 등록</button>
		</div>
	</div>
	<button id="list" onClick="freeBoardList()">목록</button>
</body>
</html>