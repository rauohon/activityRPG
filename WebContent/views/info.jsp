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
<%@ include file="nav.jsp"%>
<div style="padding-top:60px">
	<h3>나의 정보</h3>
	<input type="hidden" name="id" value="${id }" />
	${userInfo }
</div>
</body>
</html>