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
	function init() {
		var userType = "${userType}";
		if (userType == 2) {
			$("#nomar_user").css("display", "none");
			$("#admin_user").css("display", "");
		} else {
			$("#nomar_user").css("display", "");
			$("#admin_user").css("display", "none");
		}
	}
	
	/* 정지 된 회원 리스트 */
	function stop(id){
		alert(id);
		var form = createForm("stopListForm", "stopList", "post");
		createinput("hidden", "id", id);
		relationObj("stopListForm", "id");
		form.submit();
	}

	/* 회원 리스트 */
	function start(id) {
		createForm("startListForm", "userCheck", "post");
		var form = document.getElementsByName("startListForm")[0];
		form.submit();
	}

	/* 정지 버튼 */
<<<<<<< HEAD
	function userDelete(id) {
		alert(id);
		createForm("userDeleteForm", "UserDelete", "post");
		var form = document.getElementsByName("userDeleteForm")[0];

		/* 리스트에서 삭제시켜줄 회원 */
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "id";
		code.value = id;

		form.appendChild(code);
		form.submit();
	}

	/* 복귀 버튼 */
	function userRestart(id) {
		alert(id);
		createForm("userRestartForm", "userRestart", "post");
		var form = document.getElementsByName("userDeleteForm")[0];

		/* 리스트에서 삭제시켜줄 회원 */
		var user = document.createElement("input");
		user.type = "hidden";
		user.name = "id";
		user.value = id;

		form.appendChild(user);
		form.submit();
	}
=======
	function userDelete(id){
	      createForm("userDeleteForm", "UserDelete", "post");
	      var form = document.getElementsByName("userDeleteForm")[0];
	
	      /* 리스트에서 삭제시켜줄 회원 */
	      var code = document.createElement("input");
	      code.type = "hidden";
	      code.name = "id";
	      code.value = id;
	      
	      form.appendChild(code);
	      form.submit();
	   }
	
	/* 복귀 버튼 */
	function userRestart(id){
	      createForm("userRestartForm", "userRestart", "post");
	      var form = document.getElementsByName("userRestartForm")[0];
	
	      /* 리스트에서 삭제시켜줄 회원 */
	      var user = document.createElement("input");
	      user.type = "hidden";
	      user.name = "id";
	      user.value = id;
	      
	      form.appendChild(user);
	      form.submit();
	   }
>>>>>>> 48d9e3c7d8f8981d1f8656655d7d99b758679922
</script>

</head>
<body onLoad="init()">
<%@ include file="nav.jsp"%>
	<div class="contents">
	<!-- 종 -->
	<div>
		${MemberList }
	</div>
	<!-- 종 -->
	</div>
</body>
</html>