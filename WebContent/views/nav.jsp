<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	form.submit();
}
</script>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<div id="layer_fixed">
		<table id="table">
			<tr>
				<td><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></td>
				<td><button onClick="total('freeBoardForm', 'freeBoard', 'post')" id="bar">BOARD</button></td>
				<td><button onClick="total('gameForm', 'GameForm', 'post')" id="bar">GAME PLAY</button></td>
			</tr>
		</table>
	</div>