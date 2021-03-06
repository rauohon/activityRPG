<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/common.js"></script>
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
	relationObj("eventBoardListForm", "id");
	relationObj("questionBoardForm", "id");
	form.submit();
}
	
	//회원 관리
	function userCheck() {
		createForm("userCheckForm", "userCheck", "post");
		var form = document.getElementsByName("userCheckForm")[0];
		form.submit();
	}
	
	//이벤트 게시판
	function eventBoardList() {
		createForm("eventBoardListForm", "eventBoardList", "post");
		var form = document.getElementsByName("eventBoardListForm")[0];
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
	//한광수
	function reportBoardMove() {
		createForm("reportBoardForm", "ReportBoard", "post");

		var form = document.getElementsByName("reportBoardForm")[0];
		form.submit();
	}
	//한광수
	//김형석
	function questionBoardMove() {
    	createForm("questionBoardForm", "QuestionBoard", "post");

    	var form = document.getElementsByName("questionBoardForm")[0];

    	form.submit();
 	}
	//김형석
</script>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<div id="layer_fixed">
	<div id='nomar_user'>
		<ul style='text-align: center;'>
			<li><button onClick="total('mainForm', 'activityRPG', 'post')" id="bar">MAIN PAGE</button></li>
			<li><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></li>
			<li><button onClick="total('gameForm', 'GameForm', 'post')" id="bar">GAME PLAY</button></li>
			<li><button id="bar">BOARD</button>
				<ul>
					<li><input type="button" value="공지사항 게시판" onClick="newsBoardMove()"  id="bar" /></li>
					<li><button onClick="total('eventBoardListForm', 'eventBoardList', 'post')" id="bar">이벤트 관리</button></li>
					<li><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">자유게시판</button></li>
					<li><button onClick="total('questionBoardForm', 'QuestionBoard', 'post')" id="bar">1:1문의</button></li>
					<li><button onClick="total('guildboardpage', 'GuildBoardPage', 'get')" id="bar">길드게시판</button></li>
					<li><input type="button" value="공략 게시판" onClick="attackBoardMove()" id="bar" /></li>
					<li><input type="button" value="신고 게시판" onClick="reportBoardMove()" id="bar" /></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id='admin_user'>
		<ul style='text-align: center;'>
			<li><button onClick="total('mainForm', 'adminMain', 'post')" id="bar">MAIN PAGE</button></li>
			<li><input type="button" value="회원 관리" onClick="userCheck()"  id="bar" /></li>
			<li><input type="button" value="공지사항 관리" onClick="newsBoardMove()"  id="bar" /></li>
			<li><button onClick="total('eventBoardListForm', 'eventBoardList', 'post')" id="bar">이벤트 관리</button></li>
			<li><button onClick="total('questionBoardForm', 'QuestionBoard', 'post')" id="bar">1대1문의 관리</button></li>
			<li><button id="bar">게시판 관리</button>
				<ul>
					<li><button onClick="total('guildboardpage', 'GuildBoardPage', 'get')" id="bar">길드게시판</button></li>
					<li><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">자유게시판</button></li>
					<li><input type="button" value="공략 게시판" onClick="attackBoardMove()" id="bar" /></li>
					<li><input type="button" value="신고 게시판" onClick="reportBoardMove()" id="bar" /></li>
				</ul>
			</li>
			<li><button onClick="total('adminactivitylogpage','AdminActivityLogPage','post')" id="bar">운동 통계 정보</button>
			</li>
		</ul>
	</div>
</div>