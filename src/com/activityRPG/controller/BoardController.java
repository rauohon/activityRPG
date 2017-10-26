/**
 * 
 */
package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.services.GuildBoard;

/**
 * @클래스명 : Board
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class BoardController {
	
	@Autowired
	private GuildBoard gBoard;
	private ModelAndView mav = new ModelAndView();
	
	/**
	 * 처리내용 : GuildBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : backPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/GuildBoardPage", method = RequestMethod.GET)
	private ModelAndView backPage(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(0, bean);
		
		return mav;
	}
	
	
	/**
	 * 처리내용 : writeGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/WriteGBoardPage", method = RequestMethod.GET)
	private ModelAndView writeGBoardPage(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(1, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : readGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ReadGBoardPage", method = RequestMethod.POST)
	private ModelAndView readGBoardPage(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(2, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : confirmDeleteGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : confirmDeleteGBoard
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ConfirmDeleteGBoardPage", method = RequestMethod.GET)
	private ModelAndView confirmDeleteGBoardPage(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(3, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : ConfirmModifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : ConfirmModifyGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ConfirmModifyGBoardPage", method = RequestMethod.GET)
	private ModelAndView confirmModifyGBoardPage(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(4, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : DeleteGBoard연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : deleteGBoard
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/DeleteGBoard", method = RequestMethod.GET)
	private ModelAndView deleteGBoard(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(5, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : modifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : modifyGBoard
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ModifyGBoardPage", method = RequestMethod.GET)
	private ModelAndView modifyGBoardPage(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(6, bean);
		
		return mav;
	}

}
