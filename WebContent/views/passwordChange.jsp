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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="css/passwordChange.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script src="js/passwordChange.js"></script>
<script>
	function infoBack() {
		var form = createForm("infoBackForm", "infoBack", "post");

		createObj("hidden", "id", "${id}", "");

		relationObj("infoBackForm", "id");
		form.submit();
	}

	function change() {
		var form = createForm("changeForm", "changePwd", "post");
		var pwd = document.getElementsByName("pwd")[0];
		createinput("hidden", "id", "${id }");
		relationObj("changeForm", "id");
		relationObj("changeForm", "pwd");
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
	<div id="pwdChange" style="padding-top:70px;">
		<!-- <div class="form-group">
			<input type="password" name="pw" placeholder="현재 패스워드" />
		</div> -->
		
		<!-- 패스워드 -->
		<form id="changePwd" action="changePwd" method="post">
		
		<div>
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
					<input name="pwd2" id="spassword" type="password" class="form-control" placeholder="변경 할 패스워드 재 입력">
				</div>
				<span class="help-block" id="error"></span>
			</div>
		</div>
		<input type="hidden" value="${id }" />
		<button id="btn" onClick="change()">패스워드 변경</button><br>
		<button id="btn" onClick="infoBack()">나의 정보로 돌아가기</button>
		</form>
	</div>
</body>
</html>