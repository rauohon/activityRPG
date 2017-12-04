<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Battle Page 2</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<link rel="stylesheet" type="text/css" href="css/battlePage2.css"/>
<link href="https://fonts.googleapis.com/css?family=Spectral+SC:200i" rel="stylesheet"> <!-- 폰트 -->
<script src="js/common.js"></script>
<script>
	function openBox(){
	  $(function(){
			$("div").effect("explode", "slow");
		});
	  	setTimeout(function() {
	  		createForm("boxOpenForm", "BoxOpen", "post");
			var form = document.getElementsByName("boxOpenForm")[0];
			form.submit();
	 	}, 550);
	}
	function pass(){
		$(function(){
			$("div").effect("drop", "slow");
		});
		setTimeout(function(){
			createForm("runForm", "Run", "post");
			var form = document.getElementsByName("runForm")[0];
			form.submit();
		}, 550);
	}
</script>
<style>
	
</style>
<body>
	<div id="box1">
		<img src="images/chest.png" width="100%" height="100%" alt="보물상자" title="보물상자" />
	</div>
	<div id="box2">
		<br/>
		<a>보물상자를 발견했다!</a><br/>
		<button onClick="openBox()">연다</button>
		<button onClick="pass()">지나간다</button>
	</div>
</body>
</html>