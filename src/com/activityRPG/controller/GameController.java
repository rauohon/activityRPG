package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BattleBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.services.GameNomalService;
import com.activityRPG.services.GamePlayService;
import com.activityRPG.utils.ProjectUtils;
import com.google.gson.Gson;

/**
 * @클래스명 : GameController
 * @작성일 : 2017. 10. 21.
 * @설명 : 
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
	@RequestMapping(value="/GameForm", method = RequestMethod.POST)
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
	@RequestMapping(value="/DungeonPage", method = RequestMethod.POST)
	private ModelAndView dungeonPage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(4, gameBean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 캐릭터 정보 페이지 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterInfoPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/CharacterInfo", method = RequestMethod.GET)
	private ModelAndView characterInfoPage(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(5, gameBean);

		return mav; 
	}
	
	/**
	 * 처리내용 : 아이템 정보 불러오는 ajax
	 * 작성일 : 2017. 10. 25.
	 * 작성자 : 신태휘
	 * @Method Name : itemInfo
	 * @return type : String
	 */
	@RequestMapping(value="/ItemInfo", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	private ModelAndView itemInfo(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(6, gameBean);
		
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
	 * 처리내용 : 던전 페이지 내부 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : movement
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/Movement", method = RequestMethod.POST)
	private ModelAndView movement(@ModelAttribute GameBean gameBean) {
		
		mav=gp.entrance(0, gameBean);

		return mav; 
	}
	
	/**
	 * 처리내용 : 아이템 사용
	 * 작성일 : 2017. 11. 1.
	 * 작성자 : 신태휘
	 * @Method Name : itemUse
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ItemUse", method = RequestMethod.POST)
	private ModelAndView itemUse(@ModelAttribute GameBean gameBean) {
		
		mav=gp.entrance(1, gameBean);
		
		return mav;
	}

	
	/**
	 * 처리내용 : 장착된 아이템 해제
	 * 작성일 : 2017. 11. 3.
	 * 작성자 : 신태휘
	 * @Method Name : itemDisArm
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ItemDisArm", method = RequestMethod.POST)
	private ModelAndView itemDisArm(@ModelAttribute GameBean gameBean) {
		
		mav=gp.entrance(2, gameBean);
		
		return mav;
	}
	
	//*******************************김훈******************************
	//캐릭터 생성 폼 이동
		@RequestMapping(value="/CharacterCreateFormMove", method = RequestMethod.POST)
		private ModelAndView characterCreateFormMove(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gn.entrance(41, gameBean);
			return mav;
		}
		
		//캐릭터 생성
		@RequestMapping(value="/CharacterCreate", method = RequestMethod.POST)
		private ModelAndView characterCreate(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gn.entrance(42, gameBean);
			return mav;
		}
		
		//강화 상점 이동
		@RequestMapping(value="/EnhanceShop", method = RequestMethod.GET)
		private ModelAndView enhanceShopEnter(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gp.entrance(42, gameBean);
			return mav;
		}
		
		//아이템 정보
		@RequestMapping(value="/ItemInformation", method = RequestMethod.POST, produces="application/text; charset=UTF-8")
		private ModelAndView getItemInformation(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gp.entrance(43, gameBean);
			return mav;
		}
		
		//강화 시작
		@RequestMapping(value="/Enhance", method = RequestMethod.POST)
		private ModelAndView enhance(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gp.entrance(44, gameBean);
			return mav;
		}
		
		//전투 페이지 이동
		@RequestMapping(value="/BattlePage", method = RequestMethod.GET)
		private ModelAndView battlePage(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gp.entrance(45, gameBean);
			return mav;
		}
		
		//스킬 목록 보기
		@RequestMapping(value="/SkillMenu", method = RequestMethod.POST, produces="application/text; charset=UTF-8")
		private ModelAndView skillMenu(@ModelAttribute GameBean gameBean, @ModelAttribute BattleBean battleBean) {
			mav = new ModelAndView();
			mav = gp.entrance(46, gameBean, battleBean);
			return mav;
		}
		
		//스킬 사용
		@RequestMapping(value="/UseSkill", method = RequestMethod.POST)
		private ModelAndView useSkill(@ModelAttribute GameBean gameBean, @ModelAttribute BattleBean battleBean) {
			mav = new ModelAndView();
			mav = gp.entrance(47, gameBean, battleBean);
			return mav;
		}
		
		//전투에서 도망
		@RequestMapping(value="/Run", method = RequestMethod.POST)
		private ModelAndView run() {
			mav = new ModelAndView();
			mav.setViewName("dungeon");
			return mav;
		}
		
		//전투 페이지2 이동
		@RequestMapping(value="/BattlePage2", method = RequestMethod.GET)
		private ModelAndView battlePage2() {
			mav = new ModelAndView();
			mav.setViewName("battlePage2");
			return mav;
		}
		
		//상자 열기
		@RequestMapping(value="/BoxOpen", method = RequestMethod.POST)
		private ModelAndView boxOpen() {
			mav = new ModelAndView();
			mav = gp.entrance(48);
			return mav;
		}
	//*******************************김훈******************************
}
