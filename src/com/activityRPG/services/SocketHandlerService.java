/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;


/**
 * @클래스명 : SocketHandlerService
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : SocketHandlerService
 */
@Service
public class SocketHandlerService {
	private ModelAndView mav = new ModelAndView();
	@Autowired
	private GameNomalService gn;
	public WebSocketMessage<String> entrance(String jobCode, String msg) {
		WebSocketMessage<String>reponse = null;
		int i = Integer.parseInt(jobCode);
		switch(i) {
			case 0:
				reponse = new TextMessage(msg);
				break;
			case 1:
				reponse = new TextMessage(msg);
				break;				
		}
		
		return reponse;
	}

}
