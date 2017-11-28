package com.activityRPG.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BattleBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.services.GameNomalService;
import com.activityRPG.services.GamePlayService;
import com.activityRPG.services.QuestService;

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
	@Autowired
	private QuestService questService;
	
	private ModelAndView mav = new ModelAndView();

	@RequestMapping(value="/gameGoGo", method = RequestMethod.POST)
	private ModelAndView gameGoGo(@ModelAttribute GameBean gameBean) {
		
		mav = gn.entrance(72, gameBean);
		
		return mav;
	}
	
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
	 * 처리내용 : 캐릭터의 사진 파일 업로드
	 * 작성일 : 2017. 11. 13.
	 * 작성자 : 신태휘
	 * @Method Name : charaImgFileUpload
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/CharaImgFileUpload", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	private ModelAndView charaImgFileUpload(@ModelAttribute GameBean gameBean, HttpServletRequest request, HttpSession session) {
		
		mav = gn.entrance(7, gameBean, request, session);
		
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
	//*****************************한광수******************************
		
		//전체 퀘스트  리스트
		@RequestMapping(value="/QuestAll", method = RequestMethod.POST)
		private ModelAndView questAll() {
			
			mav = new ModelAndView();
			mav = questService.entrance(61);
			return mav;
		}
		//퀘스트 받기
		@RequestMapping(value="/QuestAdd", method = RequestMethod.POST)
		private ModelAndView questAdd(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = questService.entrance(62, gameBean);
			return mav;
		}
		//내퀘스트 완료
		@RequestMapping(value="/QuestCheck", method = RequestMethod.POST)
		private ModelAndView questCheck(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = questService.entrance(63, gameBean);
			return mav;
		}
		//퀘스트 보상
		@RequestMapping(value="/QuestPresent", method = RequestMethod.POST)
		private ModelAndView questPresent(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = questService.entrance(64, gameBean);
			return mav;
		}
		//내 퀘스트 리스트
		@RequestMapping(value="/MyQuestList", method = RequestMethod.POST)
		private ModelAndView myQuestList() {
			
			mav = new ModelAndView();
			mav = questService.entrance(65);
			return mav;
		}
		//랭킹 
		@RequestMapping(value="/Ranking", method = RequestMethod.POST)
		private ModelAndView ranking(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = gn.entrance(66, gameBean);
			return mav;
		}
		//길드목록
		@RequestMapping(value="/Guild", method = RequestMethod.POST)
		private ModelAndView guild(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = gn.entrance(67, gameBean);
			return mav;
		}
		//길드생성이동
		@RequestMapping(value="/GuildCreateFormMove", method = RequestMethod.POST)
		private ModelAndView guildCreateFormMove() {
			
			mav = new ModelAndView();
			mav.setViewName("guildCreate");
			return mav;
		}
		//길드 생성
		@RequestMapping(value="/GuildCreate", method = RequestMethod.POST)
		private ModelAndView guildCreate(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = gn.entrance(68, gameBean);
			return mav;
		}
		//길드가입
		@RequestMapping(value="/GuildJoinMove", method = RequestMethod.POST)
		private ModelAndView guildJoinMove(@ModelAttribute GameBean gameBean) {
			
			mav = new ModelAndView();
			mav = gn.entrance(69, gameBean);
			return mav;
		}
		
		//길드 탈퇴
		@RequestMapping(value="/GuildOut", method = RequestMethod.POST)
		private ModelAndView guildOut(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gn.entrance(70, gameBean);
			return mav;
		}
		//길드 멤버 리스트 출력
		@RequestMapping(value="/GuildMemberMove", method = RequestMethod.POST)
		private ModelAndView guildMemberMove(@ModelAttribute GameBean gameBean) {
			mav = new ModelAndView();
			mav = gn.entrance(71, gameBean);
			return mav;
		}
		//**********************************한광수*****************
		//*******************************김형석******************************
		@RequestMapping(value="/ShopEquip", method = RequestMethod.GET)
		private ModelAndView shopEquip(@ModelAttribute GameBean gameBean) {
			/*//상품 목록 정보 취득
	        List<GameBean> itemList = this.gp.getItemList();
	        
	        //모델 생성
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("itemList", itemList);
	        
	        //반환값인 ModelAndView 인스턴스 생성
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("shopWeapon");
	        modelAndView.addAllObjects(model);*/
	        
			mav = gp.entrance(21, gameBean);
			return mav;
		}
		
		@RequestMapping(value="/EquipBuy", method = RequestMethod.POST)
		private ModelAndView equipBuy(@ModelAttribute GameBean gameBean) {
			
			mav = gp.entrance(22, gameBean);
	        return mav;
		}
		
		@RequestMapping(value="/EquipSell", method = RequestMethod.POST)
		private ModelAndView equipSell(@ModelAttribute GameBean gameBean) {
			
			mav = gp.entrance(23, gameBean);
	        return mav;
		}
		//*******************************김형석******************************
}
