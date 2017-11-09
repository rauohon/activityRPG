<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>freeBoardContent</title>
<script src="js/common.js"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/freeBoardList.css" media="screen" type="text/css" />
<script>
	function freeUpdate(id, code, title, content, date){
		alert(code);
		var form = createForm("freeUpageForm", "freeUpdate", "post");
		
		createinput("hidden", "id", id);
		createinput("hidden", "code", code);
		createinput("hidden", "title", title);
		createinput("hidden", "content", content);
		createinput("hidden", "date", date);
		
		relationObj("freeUpageForm", "id");
		relationObj("freeUpageForm", "code");
		relationObj("freeUpageForm", "title");
		relationObj("freeUpageForm", "content");
		relationObj("freeUpageForm", "date");
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
		<h2>자유게시판 글 상세보기</h2>
		${freecontent }
		${message }
	</div>
</body>
</html>