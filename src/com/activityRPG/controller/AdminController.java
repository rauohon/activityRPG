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

	//회원 관리 페이지 >> 타입 1
	@RequestMapping(value = "/userCheck", method = RequestMethod.POST)
	public ModelAndView userCheck(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("AdminController :: 회원 관리 페이지");
		mav = am.entrance(2, member);
		return mav;
	}
	
	//회원 정지 버튼 눌렀을 때
	@RequestMapping(value = "/UserDelete", method = RequestMethod.POST)
	public ModelAndView userDelete(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("AdminController :: 회원 정지 버튼 눌렀을 때");
		mav = am.entrance(3, member);
		return mav;
	}
	
	//회원 관리 페이지 >> 타입3
	@RequestMapping(value = "/stopList", method = RequestMethod.POST)
	public ModelAndView stopList(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("AdminController :: 회원 관리 페이지 >> 타입3");
		mav = am.entrance(4, member);
		return mav;
	}
	
	//회원 복귀 버튼 눌렀을 때
	@RequestMapping(value = "/userRestart", method = RequestMethod.POST)
	public ModelAndView userRestart(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("AdminController :: 회원 관리 페이지 >> 타입3");
		mav = am.entrance(5, member);
		return mav;
	}
}
