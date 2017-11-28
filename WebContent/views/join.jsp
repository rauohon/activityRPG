<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JoinFormPage</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
<link rel="stylesheet"
	  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	  crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
<script src="js/common.js"></script>
<script src="js/join.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="css/join.css" media="screen" type="text/css" />
	
<script>
 	function btnId(){
		var form = createForm("idForm", "IdCheck", "post");
		//현재 페이지명 저장 hidden개체 생성
		createObj("hidden", "", "home", "");
		//생성 된 폼과 요소 개체간의 연결
		relationObj("idForm", "id");
		//서버 전송
		form.submit();
	}
	
/* 	$(document).ready(function() {
		$("button").click(function() {
			alert($('#id').val());
			var id = $('#id').val();
			$.ajax({
				type : "POST",
				url : "join.jsp",
				data : "id=" + id,
				cache : false,
				success : function(data) {
					alert(id + " 는 사용중인 아이디 입니다.")
				}
			});
		});
	});	*/
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
	<div class="container" style="padding-top:60px">
		<div class="signup-form-container">

			<!-- form start -->
			<form role="form" id="register-form" autocomplete="off" action="Join" method="post">
				<div class="form-header">
					<h3 class="form-title" style="margin-top: 30px; margin-bottom: 30px">
						<i class="fa fa-user"></i> Sign Up
					</h3>
					${message }
				</div>

				<div class="form-body">

					<!-- 아이디 -->
						<div class="form-group" style="width: 990px">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</div>
								<div>
									<input name="id" id="id" value="${userid }" type="text" class="form-control" placeholder="UserId">
									<input type="button" onClick="btnId()" value="중복확인" style="margin-left: 28px" />
									
								</div>
								<h5>${idchecked }</h5>
							</div>
							<span class="help-block" id="error"></span>
						</div>
					
					<!-- 패스워드 -->
					<div class="row" style="width: 1075px;">
						<!-- 패스워드 입력 -->
						<div class="form-group col-lg-6">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
								<input name="pwd" id="password" type="password" class="form-control" placeholder="Password">
							</div>
							<span class="help-block" id="error"></span>
						</div>
						<!-- 패스워드 재입력 -->
						<div class="form-group col-lg-6">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
								<input name="spwd" type="password" class="form-control" placeholder="Retype Password">
							</div>
							<span class="help-block" id="error"></span>
						</div>
					</div>

					<!-- 사용자 이름 -->
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-user"></span>
							</div>
							<input name="name" type="text" class="form-control" placeholder="userName">
						</div>
						<span class="help-block" id="error"></span>
					</div>

					<!-- 생년월일 -->
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>
							<input name="birth" type="text" class="form-control" placeholder="birth">
						</div>
						<span class="help-block" id="error"></span>
					</div>

					<!-- 나이 -->
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-time"></span>
							</div>
							<input name="age" type="text" class="form-control" placeholder="age">
						</div>
						<span class="help-block" id="error"></span>
					</div>

					<!-- 성별  :: 라디오 버튼 -->
					<div class="btn-group" data-toggle="buttons"
						style="margin-bottom: 30px">
						<!-- <div>
						<span class="glyphicon glyphicon-heart"></span>
						</div> -->
						<label class="btn btn-primary">
							<input type="radio" name="gender" id="option1" value="0" checked="checked" autocomplete="off"> 남자
						</label>
						<label class="btn btn-primary">
							<input type="radio" name="gender" id="option2" value="1" autocomplete="off"> 여자
						</label>
					</div>

					<!-- 핸드폰 번호 -->
					<div class="form-group">
						<div class="input-group">

							<div class="input-group-addon">
								<span class="glyphicon glyphicon-phone"></span>
							</div>
							<input name="phone" type="text" class="form-control" placeholder="Phone">
						</div>
						<span class="help-block" id="error"></span>
					</div>

					<!-- 메일 입력 -->
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-envelope"></span>
							</div>
							<input name="email" type="text" class="form-control" placeholder="Email">
						</div>
						<span class="help-block" id="error"></span>
					</div>
				</div>

				<!-- 회원가입 버튼 -->
				<div class="form-footer">
					<button type="submit" class="btn btn-info">
						<span class="glyphicon glyphicon-log-in"></span> Sign Me Up !
					</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>