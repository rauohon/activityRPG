<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/info.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
	function total(formname, action, method) {
		var form = createForm(formname, action, method);

		createObj("hidden", "id", "${id}", "");

		relationObj("mainForm", "id");
		relationObj("msgForm", "id");
		relationObj("boardForm", "id");
		relationObj("gameForm", "id");
		relationObj("activityDayLogForm", "id");
		relationObj("enrollRaspberryPiForm", "id");
		relationObj("passwordChangeForm", "id");
		form.submit();
	}

	function nameUpdate(name) {
		var form = createForm("nameForm", "namePage", "post");
		createinput("hidden", "name", name);
		relationObj("nameForm", "name");
		form.submit();
	}

	function phoneUpdate(phone) {
		var form = createForm("phoneForm", "phonePage", "post");
		createinput("hidden", "phone", phone);
		relationObj("phoneForm", "phone");
		form.submit();
	}

	function mailUpdate(email) {
		var form = createForm("mailForm", "mailPage", "post");
		createinput("hidden", "email", email);
		relationObj("mailForm", "email");
		form.submit();
	}
	
	function infoUpdateName(name, id) {
		var form = createForm("nameForm", "infoUpdateName", "post");
		createinput("hidden", "name", name);
		createinput("hidden", "id", id);

		relationObj("nameForm", "name");
		relationObj("nameForm", "id");
		form.submit();
	}
	
	function infoUpdatePhone(phone, id) {
		var form = createForm("phoneForm", "infoUpdatePhone", "post");
		createinput("hidden", "phone", phone);
		createinput("hidden", "id", id);

		relationObj("phoneForm", "phone");
		relationObj("nameForm", "id");
		form.submit();
	}
	
	function infoUpdateMail(email, id) {
		var form = createForm("mailForm", "infoUpdateMail", "post");
		createinput("hidden", "email", email);
		createinput("hidden", "id", id);

		relationObj("mailForm", "email");
		relationObj("nameForm", "id");
		form.submit();
	}
</script>
</head>
<body>
	<div id="layer_fixed">
		<table id="table">
			<tr>
				<td><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></td>
				<td><button onClick="total('boardForm', 'board', 'post')" id="bar">BOARD</button></td>
				<td><button onClick="total('gameForm', 'game', 'post')" id="bar">GAME PLAY</button></td>
			</tr>
		</table>
	</div>
	</br></br>
	<h3>나의 정보</h3>
	${userInfo }
</body>
</html>