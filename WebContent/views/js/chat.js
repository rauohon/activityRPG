/**
 * 채팅용
 */

//웹 소켓 객체를 저장할 변수를 선언

$(function(){
	var websocket;
	//입장 버튼을 클릭했을 때 이벤트 처리 $(document).ready(function()
	$(document).ready(function(){		

		//웹 소켓 연결
		websocket = new WebSocket("ws://13.125.3.78:8080/activityRPG/chat-ws");
		//13.125.3.78:8080/activityRPG/
		//웹 소켓 이벤트 처리
		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;

	});			

	//채팅 전송 버튼을 누를 때 이벤트 처리
	$('#sendBtn').bind('click', function(){
		//nickname 과 message에 입력된 내용을 서버에 전송
//		var nick = '${characterName}';
		var nick = $('#nickname').val();
		var msg = $('#message').val();
		//메시지 전송
		websocket.send(nick + ":" + msg);
		//메시지 입력창 초기화
		$('#message').val('');
	});

	//message 창에서 Enter를 눌렀을 때도 메시지를 전송
	//키보드를 누를 때 이벤트 처리
	$('#message').keypress(function(event){
		var keycode = 
			event.keyCode?event.keyCode:event.whice;
		if(keycode == 13){
			//nickname 과 message에 입력된 내용을 서버에 전송
			var nick = $('#nickname').val();
			var msg = $('#message').val();
			//메시지 전송
			websocket.send(nick + ":" + msg);
			//메시지 입력창 초기화
			$('#message').val('');
		}
	});
});

//WebSocket이 연결된 경우 호출되는 함수
function onOpen(evt){
	console.log("웹 소켓에 연결 성공");
}

//WebSocket이 연결 해제된 경우 호출되는 함수
function onClose(evt){
	console.log("웹 소켓에 연결 해제");
}
