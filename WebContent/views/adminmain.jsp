<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/adminpage.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="js/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function total(formname, action, method) {
		var form = createForm(formname, action, method);

		createObj("hidden", "id", "${id}", "");

		var form = document.getElementsByName("adminMainForm")[0];
		var form = document.getElementsByName("eventBoardForm")[0];
		var form = document.getElementsByName("QuestionBoardForm")[0];
		relationObj("freeBoardForm", "id");
		var form = document.getElementsByName("activityLogForm")[0];
		relationObj("accessForm", "id");
		
		form.submit();
function init(){
	var userType = "${userType}";
	if(userType==2){
		 var input = document.createElement("input");
		 input.type = "button";
		 input.value = "게시글 작성";
		 input.setAttribute("onClick", "newsBoardMakeFormMove()");
		 input.setAttribute("class", "writeButton");
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
}
</script>
<style>
#user {
	color: white;
}
.see{
	text-align:center;
}
#submit {
	margin-left:0px;
	width:100px;
}
#uid {
	background-color: #f68a6f;
	padding: 5px 30px;
	padding-top: 10px;
	border-radius: 5px;
	border: 1px solid #f68a6f;
	width: 170px; 
	color:#fff;
	font-size: 15px;
}
.idcs {
	background-color: #f68a6f;
	padding: 5px 30px;
	padding-top: 10px;
	border-radius: 5px;
	border: 1px solid #f68a6f;
	width: 170px; 
	color:#fff;
	font-size: 15px;
}
</style>
</head>
<body onLoad=init()>
	<%@ include file="nav.jsp"%>
	<div style="padding-top: 70px;padding-left: 50px;">	
	<!-- 종 -->
	<div>
		${MemberList }
	</div>
	<!-- 종 -->
	<button id="submit" onClick="total('accessForm', 'AccessOut', 'post')">로그아웃</button>
	</div>
</body>
</html>