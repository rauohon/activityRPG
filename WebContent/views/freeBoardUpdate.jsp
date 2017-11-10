<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeBoardUpdate</title>
<script src="js/common.js"></script>
<link rel="stylesheet" href="css/freeBoardList.css" media="screen" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
<div style="padding-top:60px">
	<input type="hidden" name="id" value="${id }"/>
	<input type="hidden" name="code" value="${code }"/>
	<h2>자유게시판 글 수정</h2>
	<p>제목 : <input type="text" id="titles" name="title" value="${title }" /></p>
	
	<p>내용</p>
	<input type="text" id="content" name="content" value="${content }" />
	<p>날짜 : ${date }</p>
	<button id="update" onClick="freeSuccess()">수정 등록</button>
	</div>
</body>
</html>