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

		var form = document.getElementsByName("adminMainForm")[0];
		var form = document.getElementsByName("eventBoardForm")[0];
		var form = document.getElementsByName("QuestionBoardForm")[0];
		relationObj("freeBoardForm", "id");
		var form = document.getElementsByName("activityLogForm")[0];
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
	
</script>
</head>
<body>
	<div id="layer_fixed">
		<table id="table" cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td id="maintext">TEXT-RPG</td>
			</tr> -->
			<tr>
				<td><button onClick="total('adminMainForm', 'adminMain', 'post')" id="bar">MAIN PAGE</button></td>
				<!-- *************김훈******************** -->
				<td><button onClick="newsBoardMove()" id="bar">공지사항 관리</button></td>
				<!-- *************김훈******************** -->
				<td><button onClick="total('eventBoardForm', 'eventBoard', 'post')" id="bar">이벤트 관리</button></td>
				<td><button onClick="total('QuestionBoardForm', 'QuestionBoard', 'post')" id="bar">1대1문의 관리</button></td>
				<td><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">게시판 관리</button></td>
				<td><button onClick="total('activityLogForm', 'ActivityDayLogPage', 'post')" id="bar">운동 정보 확인</button></td>
			</tr>
		</table>
	</div>
	</br></br></br>
	
	<h3>로그인 된 관리자 페이지 입니다.</h3>
	
	<button id="logout" onClick="total('accessForm', 'AccessOut', 'post')">로그아웃</button>
</body>
</html>