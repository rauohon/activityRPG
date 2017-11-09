<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 리스트 페이지</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="js/common.js"></script>
<script>
	function board() {
		var form = createForm('freeBoardInsertForm', 'freeInsertPage', 'post');
		createObj("hidden", "id", "${id}", "");
		relationObj("freeBoardInsertForm", "id");
		form.submit();
	}

	function viewContents(code) {
		var form = createForm("contentsForm", "freeBoardContent", "post");
		createinput("hidden", "code", code);
		relationObj("contentsForm", "code");
		form.submit();
	}

	function freedelete(code, id) {
		var form = createForm("freeDeleteForm", "freeBoardDelete", "post");
		createinput("hidden", "code", code);
		createinput("hidden", "id", id);

		relationObj("freeDeleteForm", "code");
		relationObj("freeDeleteForm", "id");
		form.submit();
	}
	
	function titlefine() {
		alert("???????");
		var form = createForm("freeTitleFineForm", "freeTitleFine", "post");
		
			createinput("hidden", "text", "${text}");
			createinput("hidden", "type", type);
			relationObj("freeTitleFineForm", "text");
			relationObj("freeTitleFineForm", "type");
			alert(type.value);
		form.submit();
	}
</script>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<link rel="stylesheet" href="css/freeBoardList.css" media="screen" type="text/css" />
	<div style="padding-top: 60px;">
		<p>자유 게시판</p>
		${freelist }
		<div>${message }</div>
			<table class="fine" style="margin-top: 20px;">
				<thead>
					<tr>
						<td> 
							<select name="type" id="type">
								<option value="freetitle">제목</option>
								<option value="freeuser">작성자</option>
							</select>
						</td>
						<td><input type="text" name="text" /></td>
						<td><input type="button" id="button" onClick="titlefine()" value="검색" /></td>
						<td><button onClick="board()" id="click">게시판 글 쓰기</button></td>
					</tr>
				</thead>
			</table>
	</div>
</body>
</html>