<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	}
	
function init(){
	var userType = "${userType}";
	var login = document.getElementById("login")[0];
	var state = "${id }";
	
	if(userType==2){		
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
	
	if (state != "") {
		login.style.display = "block";
	} else {
		login.style.display = "none";
	}
}
</script>
</head>
<body onLoad=init()>
	
	<%@ include file="nav.jsp"%>
	<!-- <div class="contents"> -->
	<link rel="stylesheet" href="css/adminpage.css" media="screen" type="text/css" />
	<div style="padding-top: 70px; padding-left: 50px;">	
	<h3 id="login">로그인 된 관리자 페이지 입니다.</h3>
	
	<button id="logout" onClick="total('accessForm', 'AccessOut', 'post')">로그아웃</button>
	</div>
	</div>
</body>
</html>