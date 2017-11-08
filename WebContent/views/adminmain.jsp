<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/adminpage.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
	function total(formname, action, method) {
		var form = createForm(formname, action, method);

		createObj("hidden", "id", "${id}", "");

		relationObj("adminMainForm", "id");
		relationObj("", "id");
		relationObj("", "id");
		relationObj("", "id");
		relationObj("boardForm", "id");
		relationObj("activityLogForm", "id");
		relationObj("accessForm", "id");
		
		form.submit();
	}
	//*******김훈********
 	function newsBoardMove(){
		createForm("newsBoardForm", "NewsBoard", "post");
		
		var form = document.getElementsByName("newsBoardForm")[0];
		
		form.submit();
	}
 	//*********김훈***********
	
 	//종
	function userDelete(id){
		createForm("userDeleteForm", "UserDelete", "post");
		var form = document.getElementsByName("userDeleteForm")[0];
		
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "id";
		code.value = id;
		
		form.appendChild(code);
		form.submit();
	}
	function attackBoardMove(){
		createForm("attackBoardForm", "AttackBoard", "post");
		
		var form = document.getElementsByName("attackBoardForm")[0];
		
		form.submit();
	}
	
	//종
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
<body>
	<div id="layer_fixed">
		<table id="table" cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td id="maintext">TEXT-RPG</td>
			</tr> -->
			<tr>
				<td><button onClick="total('adminMainForm', 'adminMain', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('', '', 'post')" id="bar">공지사항 관리</button></td>
				<td><button onClick="total('', '', 'post')" id="bar">이벤트 관리</button></td>
				<td><button onClick="total('', '', 'post')" id="bar">1대1문의 관리</button></td>
				<td><button onClick="total('boardForm', 'adminFreeBoard', 'post')" id="bar">게시판 관리</button></td>
				<td><button onClick="total('activityLogForm', 'ActivityDayLogPage', 'post')" id="bar">운동 정보 확인</button></td>
			</tr>
		</table>
	</div>
	</br></br></br>
	
	<h3 id="user">관리자 페이지</h3>
	
	<!-- *************김훈******************** -->
	<input type="button" value="공지사항 게시판 이동" onClick="newsBoardMove()" />
	<!-- *************김훈******************** -->
	<!-- 종 -->
	<div>
		${MemberList }
	</div>
	<!-- 종 -->
	<button id="submit" onClick="total('accessForm', 'AccessOut', 'post')">로그아웃</button>
</body>
</html>