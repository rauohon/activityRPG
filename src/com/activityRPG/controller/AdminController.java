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

import com.activityRPG.beans.MemberBean;
import com.activityRPG.services.AdminManagement;

/**
 * @클래스명 : AdminController
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class AdminController {

	@Autowired
	private AdminManagement am;
	private ModelAndView mav = null;

	//관리자 메인 페이지
	@RequestMapping(value="/adminMain", method = RequestMethod.POST)
	public ModelAndView adminMain(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 관리자 메인 페이지");
		mav = am.entrance(1, member);
		return mav;
	}
	
	//관리자 메인 게시판(자유)
	@RequestMapping(value="/adminFreeBoard", method = RequestMethod.POST)
	public ModelAndView adminFreeBoard(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 관리자 메인 게시판(자유)");
		mav = am.entrance(2, member);
		return mav;
	}
}
