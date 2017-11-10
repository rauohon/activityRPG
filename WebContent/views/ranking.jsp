<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/backGround.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>랭킹</title>
</head>
<script>
</script>
<style>
	body{
		/* background-image: */
		background:rgba(0,0,0,5) url("/images/rankingphoto.jpg");
		background-repeat:no-repeat;
		background-size:100% 100%;
		overflow:hidden;
		/* background-attachment:fixed; */
	}
	.ranking0{
		color:gold;
	}
	.ranking1{
		color:silver;
	}
	.rankingList{
		margin-top:50px;
		text-align:center;
	
	}
	.rankingList th{
		font-size:22px;
	}
	.rankingList td{
		font-size:20px;
		
	}
	.ranking{
		background-image:url("/images/rankingListPhoto.png");
		background-repeat:no-repeat;
		background-size:100% 100%;
		margin-top:260px;
		margin-left:450px;
		width:400px;
	}
	.backButton{
		margin-left:500px;
		background-color:silver;
	}
	
</style>
<body>
	<div class="ranking">
	${rankingList }
	</div>
	<div>
	<input class="backButton" type="button" value="뒤로가기" onClick="back()">
	</div>
</body>

</html>