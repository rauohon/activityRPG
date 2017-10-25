/**
 * 
 */
package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.GameBean;
import com.activityRPG.services.GameNomalService;
import com.activityRPG.services.GamePlayService;
import com.activityRPG.utils.ProjectUtils;
import com.google.gson.Gson;

/**
 * @클래스명 : GameController
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 21.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */ 
@Controller
public class GameController {
	
	@Autowired
	private GameNomalService gn;
	@Autowired
	private GamePlayService gp;
	private ModelAndView mav = new ModelAndView();
	
	/**
	 * 처리내용 : 호출 페이지 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : rankingPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/BackPage", method = RequestMethod.GET)
	private ModelAndView backPage(@ModelAttribute GameBean gameBean) {
		
		mav=gn.entrance(0, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 마을페이지 연결
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : villagePage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/GameForm", method = RequestMethod.GET)
	private ModelAndView villagePage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(1, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 퀘스트 페이지 이동
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : guildPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/QuestPage", method = RequestMethod.GET)
	private ModelAndView questPage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(2, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 길드 관리 페이지 이동 
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : guildPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/GuildPage", method = RequestMethod.GET)
	private ModelAndView guildPage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(3, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 던전페이지 이동
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : dungeonPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/DungeonPage", method = RequestMethod.GET)
	private ModelAndView dungeonPage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(4, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 각 상점 페이지 연결
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : shopPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ShopPage", method = RequestMethod.GET)
	private ModelAndView shopPage(@ModelAttribute GameBean gameBean) {
		
		mav.setViewName("shop");
		
		return mav;
	}
	
	/**
	 * 처리내용 : 랭킹 페이지 연결
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : rankingPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/RankingPage", method = RequestMethod.GET)
	private ModelAndView rankingPage(@ModelAttribute GameBean gameBean) {
		
		mav.setViewName("ranking");
		
		return mav;
	}
	
	/**
	 * 처리내용 : 캐릭터 정보 페이지 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterInfoPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/CharacterInfo", method = RequestMethod.POST)
	private ModelAndView characterInfoPage(@ModelAttribute GameBean gameBean) {
		mav = gn.entrance(5, gameBean);

		return mav; 
	}
	
	/**
	 * 처리내용 : 던전 페이지 내부 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : movement
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/Movement", method = RequestMethod.GET)
	private ModelAndView movement(@ModelAttribute GameBean gameBean) {
		System.out.println(gameBean.getMoveValue());
		mav = gp.entrance(0, gameBean);

		return mav; 
	}
	
	/**
	 * 처리내용 : 아이템 정보 불러오는 ajax
	 * 작성일 : 2017. 10. 25.
	 * 작성자 : 신태휘
	 * @Method Name : itemInfo
	 * @return type : String
	 */
	@RequestMapping(value="/ItemInfo", method = RequestMethod.GET)
	private @ResponseBody String itemInfo(@ModelAttribute GameBean gameBean) {
		
		String result = "흐으어어억";
		
		return result;
	}
	
}
