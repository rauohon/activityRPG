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
	
	function freeUpdateBack(id, code, title, date){
		var form = createForm("freeUpageBackForm", "freeBoard", "post");
		
		createinput("hidden", "id", id);
		createinput("hidden", "code", code);
		createinput("hidden", "title", title);
		createinput("hidden", "date", date);
		
		relationObj("freeUpageBackForm", "id");
		relationObj("freeUpageBackForm", "code");
		relationObj("freeUpageBackForm", "title");
		relationObj("freeUpageBackForm", "date");
		form.submit();
	}
	
	function freeComment(code){
		var form = createForm("freeCommentForm", "freeComment", "post");
		
		createinput("hidden", "id", "${id}");
		createinput("hidden", "code", code);
		createinput("hidden", "comment", comment);
		
		relationObj("freeCommentForm", "id");
		relationObj("freeCommentForm", "code");
		relationObj("freeCommentForm", "comment");
		
		form.submit();
	}
	
	function freecommentdelete(frcode, id, code) {
		alert(code);
		alert(id);
		var form = createForm("freeDeleteForm", "freeCommentDelete", "post");
		createinput("hidden", "frcode", frcode);
		createinput("hidden", "id", id);
		createinput("hidden", "code", code);

		relationObj("freeDeleteForm", "frcode");
		relationObj("freeDeleteForm", "id");
		relationObj("freeDeleteForm", "code");
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
	<div class="contents">
	<div style="padding-top: 90px">
		${freecontent }
		${freecomment }
		${message }
	</div>
	</div>
</body>
</html>