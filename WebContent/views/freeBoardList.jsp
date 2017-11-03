<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 리스트 페이지</title>
<script src="js/common.js"></script>
<script>
	function total(formname, action, method) {
		var form = createForm(formname, action, method);

		createObj("hidden", "id", "${id}", "");

		relationObj("mainForm", "id");
		relationObj("msgForm", "id");
		relationObj("freeBoardForm", "id");
		relationObj("gameForm", "id");
		form.submit();
	}

	function total() {
		var form = createForm('freeBoardInsertForm', 'freeInsertPage', 'post');
		createObj("hidden", "id", "${id}", "");
		relationObj("freeBoardInsertForm", "id");
		form.submit();
	}

	function viewContents(code) {
		alert(code);
		var form = createForm("contentsForm", "freeBoardContent", "post");
		createinput("hidden", "code", code)

		relationObj("contentsForm", "code");
		form.submit();
	}

	function freedelete(code, id) {
		alert(code);
		var form = createForm("freeDeleteForm", "freeBoardDelete", "post");
		createinput("hidden", "code", code)
		createinput("hidden", "id", id);

		relationObj("freeDeleteForm", "code");
		relationObj("freeDeleteForm", "id");
		form.submit();
	}
</script>
</head>
<body>
<table id="table">
			<tr>
				<td><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></td>
				<td><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">BOARD</button></td>
				<td><button onClick="total('gameForm', 'game', 'post')" id="bar">GAME PLAY</button></td>
			</tr>
		</table>
	</div>
	</br></br></br>
	<button id="click" onClick="total()">게시판 글 쓰기</button>
	${freelist }
	<div>${message }</div>
</body>
</html>