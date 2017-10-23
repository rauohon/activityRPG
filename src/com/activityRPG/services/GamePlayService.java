/**
 * 
 */
package com.activityRPG.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.activityRPG.beans.GameBean;

/**
 * @클래스명 : GamePlayService
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class GamePlayService {
	ModelAndView mav = new ModelAndView();

	/**
	 * 처리내용 : 게임 플레이 서비스 분기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {
		
		switch(i) {
			case 0:
				mav = movement((GameBean)bean);
				break;
			case 1:
				break;
		
		}
		
		return mav;
	}

	/**
	 * 처리내용 : 0. 던전내부 이동 처리
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : movement
	 * @return type : ModelAndView
	 */
	private ModelAndView movement(GameBean bean) {
		RedirectView rv = null;
		int random = 18; 	//(int) (Math.random()*10)+3;
		int moveValue = Integer.parseInt(bean.getMoveValue());
		if((random%moveValue) == 0) {
			System.out.println("일반 전투");
			rv = new RedirectView("/DungeonPage");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}else if((random%moveValue) == 2) {
			System.out.println("보스 스테이지");
			rv = new RedirectView("/DungeonPage");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}else {
			System.out.println("보물");
			rv = new RedirectView("/GameForm");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}
		
		return mav;
	}

}
