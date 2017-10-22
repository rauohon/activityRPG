/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.GameBean;
import com.activityRPG.dao.IMBatisDao;

/**
 * @클래스명 : GameNomalService
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class GameNomalService {
	ModelAndView mav = new ModelAndView();;
	@Autowired
	private IMBatisDao dao;
	/**
	 * 처리내용 : 게임 일반 서비스 분기
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {
		
		
		switch(i) {
		
		case 0:
			mav = villagePage((GameBean)bean);
			break;
		case 1:
			mav = questPage((GameBean)bean);
			break;
		case 2:
			mav = guildPage((GameBean)bean);
			break;
		case 3:
			mav = DungeonPage((GameBean)bean);
			break;
		}
		
		
		return mav;
	}

	/**
	 * 처리내용 : 3. dungeonPage
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : DungeonPage
	 * @return type : ModelAndView
	 */
	private ModelAndView DungeonPage(GameBean bean) {
		
		mav.setViewName("dungeon");
		
		return mav;
	}

	/**
	 * 처리내용 : 2. guildPage
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : guildPage
	 * @return type : ModelAndView
	 */
	private ModelAndView guildPage(GameBean bean) {
		//if("길드 가입 여부 확인"){
		// 가입 안했으면
		mav.setViewName("guildCreate");
		//}else{
		// 가입 했으면
		//}
		return mav;
	}

	/**
	 * 처리내용 : 1. questPage 호출
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : QuestPage
	 * @return type : ModelAndView
	 */
	private ModelAndView questPage(GameBean bean) {
		
		mav.setViewName("quest");
		
		return mav;
	}

	/**
	 * 처리내용 : 0. villagePage 호출
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : villagePage
	 * @return type : ModelAndView
	 */
	private ModelAndView villagePage(GameBean bean) {
		//if(캐릭터 존재여부 확인)
		mav.setViewName("village");
		
		return mav;
	}
	

}
