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

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.services.GuildBoard;
import com.activityRPG.services.FreeBoard;

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
	@Autowired
	private FreeBoard fb;
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
	 * 처리내용 : writeGBoard 작성
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/WriteGBoard", method = RequestMethod.GET)
	private ModelAndView writeGBoard(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(2, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : readGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ReadGBoardPage", method = RequestMethod.GET)
	private ModelAndView readGBoardPage(@ModelAttribute BoardBean bean) {
		
		System.out.println("컨트롤러 글번호 확인 :: " + bean.getGbCode());
		
		mav=gBoard.entrance(3, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : ModifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : ConfirmModifyGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ModifyGBoardPage", method = RequestMethod.POST)
	private ModelAndView modifyGBoardPage(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(4, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : ModifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : ConfirmModifyGBoardPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ModifyGBoard", method = RequestMethod.POST)
	private ModelAndView modifyGBoard(@ModelAttribute BoardBean bean) {
		mav=gBoard.entrance(5, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : DeleteGBoard연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : deleteGBoard
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/DeleteGBoard", method = RequestMethod.POST)
	private ModelAndView deleteGBoard(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(6, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 댓글 작성
	 * 작성일 : 2017. 10. 30.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoard
	 * @return type : String
	 */
	@RequestMapping(value="/ReplyGBoard", method = RequestMethod.POST )
	private ModelAndView replyGBoard(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(7, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 댓글 수정
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : replyDelete
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ReplyDelete", method = RequestMethod.POST )
	private ModelAndView replyDelete(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(8, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 파일 업로드
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : fileUploadGBoard
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/UploadFileGBoard", method = RequestMethod.POST )
	private ModelAndView fileUploadGBoard(@ModelAttribute BoardBean bean) {
		
		mav=gBoard.entrance(9, bean);
		
		return mav;
	}
	
	//회원 메인 게시판 리스트(자유)
	@RequestMapping(value="/freeBoard", method = RequestMethod.POST)
	public ModelAndView freeBoardList(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 메인 게시판 리스트(자유)");
		mav = fb.entrance(1, board);
		return mav;
	}
	//회원 메인 게시판(자유)
	@RequestMapping(value="/freeContent", method = RequestMethod.POST)
	public ModelAndView freeBoardContent(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 내용 보기");
		mav = fb.entrance(2, board);
		return mav;
	}
}
