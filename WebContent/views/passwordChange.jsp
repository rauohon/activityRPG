<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>password Change</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
<script src="js/common.js"></script>
<script src="js/join.js"></script>
<script>
	function change(id, pwd) {
		var form = createForm("changeForm", "changePwd", "post");
		createinput("hidden", "id", "${id }");
		createinput("hidden", "pwd", pwd);

		relationObj("changeForm", "id");
		relationObj("changeForm", "pwd");
		form.submit();
	}
</script>
</head>
<body>
	<div id="pwdChange">
		<!-- <div class="form-group">
			<input type="password" name="pw" placeholder="현재 패스워드" />
		</div> -->
		<!-- 패스워드 -->
		<form action="changePwd" method="post">
		<div class="row" style="width: 1075px;">
			<!-- 패스워드 입력 -->
			<div class="form-group col-lg-6">
				<div class="input-group">
					<input name="pwd" id="password" type="password" class="form-control" placeholder="변경 할 패스워드">
				</div>
				<span class="help-block" id="error"></span>
			</div>
			<!-- 패스워드 재입력 -->
			<div class="form-group col-lg-6">
				<div class="input-group">
					<input name="changeSpwd" type="password" class="form-control" placeholder="변경 할 패스워드 재 입력">
				</div>
				<span class="help-block" id="error"></span>
			</div>
		</div>
		<input type="hidden" value="${id }" />
		<input type="button" onClick="change()" value="패스워드 변경" />
		</form>
	</div>
</body>
</html>