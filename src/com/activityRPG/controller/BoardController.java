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
	private FreeBoard fb;
	private ModelAndView mav = null;
	
	//회원 메인 게시판 리스트(자유)
	@RequestMapping(value="/freeBoard", method = RequestMethod.POST)
	public ModelAndView freeBoardList(@ModelAttribute BoardBean board) throws Exception {
		System.out.println("MemberController :: 메인 게시판 리스트(자유)");
		mav = fb.entrance(1, board);
		return mav;
	}
	
	//회원 메인 게시판(자유)
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
}
