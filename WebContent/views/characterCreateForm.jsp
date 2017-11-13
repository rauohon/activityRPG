<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> <!-- 움직이는 배경을 위함 -->
<link rel="stylesheet" href="/css/createCharacterForm.css" /> <!-- 움직이는 배경을 위함 -->
<script src="/js/jquery.particleground.js"></script>	<!-- 움직이는 배경을 위함 -->
<script src="/js/jquery.particleground2.js"></script>	<!-- 움직이는 배경을 위함 -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- alert창 변경 -->
<title>Character Create Form</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script>
	function init(){
		var sex = ${userSex };
		if(sex == 1){ //여자
			var girl = document.getElementById("girl");
			girl.src = "/images/woman.png";
			girl.width = 500;
			girl.height = 600;
			girl.alt = "여자 사진입니다.";
			girl.title = "여자 사진";
		}else if(sex == 0){
			//남자
			var boy = document.getElementById("boy");
			boy.src = "/images/man.png";
			boy.width = 500;
			boy.height = 600;
			boy.alt = "남자 사진입니다.";
			boy.title = "남자 사진";
		}	
		
		var userType = "${userType}";
		if(userType==2){
			 $("#nomar_user").css("display","none");
			 $("#admin_user").css("display","");
		}else{
			$("#nomar_user").css("display","");
			$("#admin_user").css("display","none");
		}
	}
	
	function characterCreate(){
		createForm("characterCreateForm", "CharacterCreate", "post");
		var form = document.getElementsByName("characterCreateForm")[0];
		
		var characterName = document.getElementsByName("characterName")[0];
		if(characterName.value == ""){
			swal({
				  title: "캐릭터 이름을 입력해주세요.",
				  icon: "warning",
				  button: "네!",
				});
		}else{
			form.appendChild(characterName);
			form.submit();
		}
	}	
	//폼 생성
	function createForm(formName, action, method){
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
	}
</script>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<div id="particles">
	<div id='wraper' style="padding-top: 60px;">	
  		<div id="intro">
			<img id="boy" />
			<img id="girl" />
			<br/>
			<a>이름  </a><input type="text" name="characterName" maxlength="6" size="25"/>
			<button onClick="characterCreate()" class="btn">생성</button><br/>			
			<font color="red" size=2>${message }</font>
  		</div>
	</div>
	</div>
</body>
</html>