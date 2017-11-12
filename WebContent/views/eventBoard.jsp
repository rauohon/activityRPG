<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eventBoard</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	function board() {
		var form = createForm('eventBoardInsertForm', 'eventBoardInsert', 'post');
		createinput("hidden", "id", "${id}");
		relationObj("eventBoardInsertForm", "id");
		form.submit();
	}
	
	function viewContents(code) {
		var form = createForm("contentsForm", "eventBoardContent", "post");
		createinput("hidden", "code", code);
		relationObj("contentsForm", "code");
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
	<link rel="stylesheet" href="css/freeBoardList.css" media="screen" type="text/css" />
<div id="setMessage" style="padding-top:60px">
   <p>eventBoard</p>
   ${eventList }
		<table class="fine" style="margin-top: 20px;">
			<thead>
				<tr>
					<td>
						<select name="type" id="type">
							<option value="eventtitle">제목</option>
							<option value="eventuser">작성자</option>
						</select>
					</td>
					<td><input type="text" name="text" /></td>
					<td><input type="button" id="button" onClick="titlefine()" value="검색" /></td>
					<td><button onClick="board()" id="click">게시판 글 쓰기</button></td>
				</tr>
			</thead>
		</table>
		<input type="hidden" value="${id }">
		</div>
</body>
</html>