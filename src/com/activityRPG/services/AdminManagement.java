/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.MemberBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : AdminManagement
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class AdminManagement extends TranEx {

	@Autowired
	private Encryption enc;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private IMBatisDao dao;

	public ModelAndView entrance(int code, Object...object) throws Exception{
		ModelAndView mav = null;
		session.setAttribute("page",((MemberBean)object[0]).getPage());
		switch(code) {
		//관리자 메인 페이지
		case 1:
			mav = adminmain((MemberBean)object[0]);
			break;
		//관리자 메인 게시판(자유)
		case 2:
			mav = adminfreeboard((MemberBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}
	
	//관리자 메인 페이지
	public ModelAndView adminmain(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 관리자 메인 페이지");
		System.out.println(mb.getId());
		try {
			if(dao.IdCheck(mb) != 0) {
				//mb.setId(session.getAttribute("id").toString());
				mav.setViewName("adminmain");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//관리자 메인 게시판(자유)
	public ModelAndView adminfreeboard(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 관리자 메인 게시판(자유)");
		System.out.println(mb.getId());
		try {
			if(dao.IdCheck(mb) != 0) {
				//mb.setId(session.getAttribute("id").toString());
				mav.setViewName("관리자 게사판 페이지 만들어야함.");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
}