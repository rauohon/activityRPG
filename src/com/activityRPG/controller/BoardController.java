package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.MemberBean;
import com.activityRPG.services.GuildBoard;
import com.activityRPG.services.NewsBoard;
import com.activityRPG.services.AttackBoard;
import com.activityRPG.services.FreeBoard;

/**
 * @클래스명 : Board
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Controller
public class BoardController {

	@Autowired
	private GuildBoard gBoard;
	@Autowired
	private FreeBoard fb;
	@Autowired
	private NewsBoard newsBoard;
	@Autowired
	private AttackBoard attackBoard;

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
	
	@RequestMapping(value="/freeBoardContent", method = RequestMethod.POST)
	public ModelAndView freeBoardContent(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 내용 보기");
		mav = fb.entrance(2, board);
		return mav;
	}

	//회원 메인 게시판(자유)
	@RequestMapping(value="/freeInsertPage", method = RequestMethod.POST)
	public ModelAndView freeBoardInsertPage(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글쓰기 페이지");
		mav = fb.entrance(3, board);
		return mav;
	}

	//자유게시판 글 등록
	@RequestMapping(value="/freeInsert", method = RequestMethod.POST)
	public ModelAndView freeBoardInsert(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글 등록");
		mav = fb.entrance(4, board);
		return mav;
	}

	//자유게시판 글 삭제
	@RequestMapping(value="/freeBoardDelete", method = RequestMethod.POST)
	public ModelAndView freeBoardDelete(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글 삭제");
		mav = fb.entrance(5, board);
		return mav;
	}

	//자유게시판 글 수정 페이지
	@RequestMapping(value="/freeUpdate", method = RequestMethod.POST)
	public ModelAndView freeUpdate(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글 수정 페이지");
		mav = fb.entrance(6, board);
		return mav;
	}

	//자유게시판 글 수정 올리기
	@RequestMapping(value="/freeUpdateCheck", method = RequestMethod.POST)
	public ModelAndView freeUpdateCheck(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글 수정 올리기");
		mav = fb.entrance(7, board);
		return mav;
	}
	
	//자유게시판 댓글 달기
	@RequestMapping(value="/freeComment", method = RequestMethod.POST)
	public ModelAndView freeComment(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 댓글 달기");
		mav = fb.entrance(8, board);
		return mav;
	}
	
	//자유게시판 글 제목 찾기
	@RequestMapping(value="/freeTitleFine", method = RequestMethod.POST)
	public ModelAndView freeTitleFine(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 자유 게시판 글 제목 찾기");
		mav = fb.entrance(9, board);
		return mav;
	}
	
/*	@RequestMapping(value="/listCmt", method={RequestMethod.GET, RequestMethod.POST})
    public String listCmt(String no, ModelMap map) {
          List listCmt = dao.getListCmt(no);
          map.put("listCmt", listCmt);
          return "reboard/listCmt";
    }
	*/
	
	//****************김훈********************
	@RequestMapping(value = "/NewsBoard", method = RequestMethod.POST)
	public ModelAndView newsBoardMove() {
		mav = new ModelAndView();
		mav = newsBoard.entrance(41);
		return mav;
	}

	@RequestMapping(value = "/NewsBoardMakeFormMove", method = RequestMethod.POST)
	public ModelAndView newsBoardMakeFormMove() {
		mav.setViewName("newsBoardMakeForm");
		return mav;
	}

	@RequestMapping(value = "/NewsBoardMake", method = RequestMethod.POST)
	public ModelAndView newsBoardMake(@ModelAttribute BoardBean boardBean, @ModelAttribute MemberBean memberBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(42, boardBean, memberBean);
		return mav;
	}

	@RequestMapping(value = "/NewsBoardContentsView", method = RequestMethod.POST)
	public ModelAndView newsBoardContentsView(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(43, boardBean);
		return mav;
	}

	@RequestMapping(value = "/NewsBoardModifyFormMove", method = RequestMethod.POST)
	public ModelAndView newsBoardModifyMove(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(44, boardBean);
		return mav;
	}

	@RequestMapping(value = "/NewsBoardModify", method = RequestMethod.POST)
	public ModelAndView newsBoardModify(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(45, boardBean);
		return mav;
	}

	@RequestMapping(value = "/NewsBoardDelete", method = RequestMethod.POST)
	public ModelAndView newsBoardDelete(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(46, boardBean);
		return mav;
	}

	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	public ModelAndView serach(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = newsBoard.entrance(47, boardBean);
		return mav;
	}
	//****************김훈********************

	//종
	@RequestMapping(value = "/AttackBoard", method = RequestMethod.POST)
	public ModelAndView attackBoardMove() {
		mav = new ModelAndView();
		mav = attackBoard.entrance(1);
		return mav;
	}

	@RequestMapping(value = "/AttackBoardMakeFormMove", method = RequestMethod.POST)
	public ModelAndView attackBoardMakeFormMove() {
		mav.setViewName("attackBoardMakeForm");
		return mav;
	}

	@RequestMapping(value = "/AttackBoardMake", method = RequestMethod.POST)
	public ModelAndView attackBoardMake(@ModelAttribute BoardBean boardBean, @ModelAttribute MemberBean memberBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(2, boardBean, memberBean);
		return mav;
	}

	@RequestMapping(value = "/AttackBoardContentsView", method = RequestMethod.POST)
	public ModelAndView attackBoardContentsView(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(3, boardBean);
		return mav;
	}

	@RequestMapping(value = "/AttackBoardModifyFormMove", method = RequestMethod.POST)
	public ModelAndView attackBoardModifyMove(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(4, boardBean);
		return mav;
	}

	@RequestMapping(value = "/AttackBoardModify", method = RequestMethod.POST)
	public ModelAndView attackBoardModify(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(5, boardBean);
		return mav;
	}

	@RequestMapping(value = "/AttackBoardDelete", method = RequestMethod.POST)
	public ModelAndView attackBoardDelete(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(6, boardBean);
		return mav;
	}

	@RequestMapping(value = "/AttackSearch", method = RequestMethod.POST)
	public ModelAndView attackserach(@ModelAttribute BoardBean boardBean) {
		mav = new ModelAndView();
		mav = attackBoard.entrance(7, boardBean);
		return mav;
	}

	//종
}
