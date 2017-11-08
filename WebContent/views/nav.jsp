<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="/js/common.js"></script>
<script type="text/javascript">
//nav 컨트롤 용
function total(formname, action, method){
	var form = createForm(formname, action, method);
	createObj("hidden", "id", "${id}", "");		
	relationObj("mainForm", "id");
	relationObj("msgForm", "id");
	relationObj("freeBoardForm", "id");
	relationObj("gameForm", "id");
	relationObj("infoForm", "id");
	relationObj("accessForm", "id");
	relationObj("characterinfo", "id");
	relationObj("guildboardpage", "id");
	form.submit();
}
//**************************김훈****************************
	function newsBoardMove() {
		createForm("newsBoardForm", "NewsBoard", "post");

		var form = document.getElementsByName("newsBoardForm")[0];

		form.submit();
	}

	function characterCreateFormMove() {
		
		createForm("characterCreateForm", "CharacterCreateFormMove", "post");

		var form = document.getElementsByName("characterCreateForm")[0];

		form.submit();
	}
	//**************************김훈****************************
	//**************************김종인****************************
	function attackBoardMove() {
		createForm("attackBoardForm", "AttackBoard", "post");

		var form = document.getElementsByName("attackBoardForm")[0];

		form.submit();
	}
	//**************************김종인****************************
</script>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<div id="layer_fixed">
	<ul style='text-align: center;'>
		<li><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></li>
		<li><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></li>
		<li><button onClick="total('gameForm', 'GameForm', 'post')" id="bar">GAME PLAY</button></li>
		<li><button id="bar">BOARD</button>
			<ul>
				<li><button onClick="total('guildboardpage', 'GuildBoardPage', 'get')" id="bar">길드게시판</button></li>
				<li><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">자유게시판</button></li>
				<li><input type="button" value="공지사항 게시판" onClick="newsBoardMove()"  id="bar" /></li>
				<li><input type="button" value="공략 게시판" onClick="attackBoardMove()" id="bar" /></li>
			</ul>
		</li>
	</ul>
</div>