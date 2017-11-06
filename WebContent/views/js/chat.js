/**
 * 채팅용
 */

//웹 소켓 객체를 저장할 변수를 선언
var websocket;

$(function(){
	//입장 버튼을 클릭했을 때 이벤트 처리 $(document).ready(function()
	$(document).ready(function(){
		//웹 소켓 연결
		websocket = new WebSocket("ws://192.168.0.237:8000/chat-ws");
		//웹 소켓 이벤트 처리

		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;

	});			

	//채팅 전송 버튼을 누를 때 이벤트 처리
	$('#sendBtn').bind('click', function(){
		//nickname 과 message에 입력된 내용을 서버에 전송
		var jobCode = $('#jobCode').val();
		var nick = $('#nickname').val();
		var msg = $('#message').val();
		//메시지 전송
		websocket.send(jobCode+"," + nick + ":" + msg);
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

//서버에서 메시지가 왔을 때 호출되는 함수
function onMessage(evt){
	//서버가 전송한 메시지 가져오기
	var data = evt.data;
	var jobIndex = data.split(",");
	//메시지를 출력
	if(jobIndex[0]=="0"){
	$('#chatEveryUserMsgArea').append(jobIndex[1] + "<br />");
	}else if(jobIndex[0]=="1")
		$('#chatGuildUserMsgArea').append(jobIndex[1] + "<br />");
}