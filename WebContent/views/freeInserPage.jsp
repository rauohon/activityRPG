<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeInserPage</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="css/writingMessage.css" media="screen" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
function freeBoardList(){
	var form = createForm("freeListForm", "freeBoard", "post");
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
<script>
function init(){
	var userType = "${userType}";
	if(userType==2){
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
}
</script>
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
	<div id="setMessage" style="padding-top:60px">
		<div class="head">
			<h2>자유게시판 글 쓰기</h2>
		</div>
		<div class="container">
			<p>글 쓰는 사람 : ${id }</p>
			<input type="text" id="a" name="title" placeholder="제목" /><br />
			<textarea rows="25" cols="110" id="b" name="content" placeholder="내용"></textarea>
			<button id="submit" onClick="insert()">게시글 등록</button>
		</div>
	</div>
	<button id="list" onClick="freeBoardList()">목록</button>
</body>
</html>