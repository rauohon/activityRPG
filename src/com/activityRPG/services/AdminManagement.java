package com.activityRPG.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.MemberBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : AdminManagement
 * @작성일 : 2017. 10. 14.
 * @설명 : 
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
			//회원 관리 페이지 >> 타입 1
		case 2:
			mav = userCheck((MemberBean)object[0]);
			break;
			//사용자 정지 버튼
		case 3:
			mav = userDelete((MemberBean)object[0]);
			break;
			//회원 관리 페이지 >> 타입 1
		case 4:
			mav = stopList((MemberBean)object[0]);
			break;
			//회원 관리 페이지 >> 타입 1
		case 5:
			mav = userrestart((MemberBean)object[0]);
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
				mav.addObject("id", mb.getId());
				mav.setViewName("adminmain");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//회원 관리 페이지 >> 타입 1
	public ModelAndView userCheck(MemberBean mb) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 회원 관리 페이지 >> 타입 1");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println("아이디 값 들어옴 >> 리스트");
				mav.addObject("MemberList", MemberList(mb));
				mav.setViewName("userBrackList");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//회원 리스트 >> 타입 1
	public String MemberList(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		List<MemberBean> listBean = dao.MemberList();
		
			sb.append("<table id=\'delete\' border=1>");
			sb.append("<tr>");
			sb.append("<th id=\"deletetitle\">아이디</th>");
			sb.append("<th id=\"deletetitle\">회원활동</th>");

			sb.append("</tr>");
			for(int i = 0; i < listBean.size(); i++) {
					sb.append("<tr>");
					sb.append("<td id=\"user\">" + listBean.get(i).getId() + "</td>");
					sb.append("<td class=\"see\">" + "<input id=\"submit\" type=\"button\" value=\"정지\" onClick=\"userDelete(\'" + listBean.get(i).getId() + "\')\" />" + "</td>");
					sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("<button id=\'list\' onClick=\"stop(\'" + mb.getId() + "\')\">" + "정지 된 회원 리스트" + "</button>");
		return sb.toString();
	}

	//회원 정지 페이지 >> 타입 3
	public ModelAndView stopList(MemberBean mb) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 회원 정지 페이지 >> 타입 3");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println("아이디 값 들어옴 >> 리스트");
				mav.addObject("MemberList", getstopList(mb));
				mav.setViewName("userBrackList");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//회원 리스트 >> 타입 3
	public String getstopList(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		List<MemberBean> listBean = dao.MemberBrackList();
			sb.append("<table id=\'delete\' border=1>");
			sb.append("<tr>");
			sb.append("<th id=\"deletetitle\">아이디</th>");
			sb.append("<th id=\"deletetitle\">회원활동</th>");

			sb.append("</tr>");
			for(int i = 0; i < listBean.size(); i++) {
					sb.append("<tr>");
					sb.append("<td id=\"user\">" + listBean.get(i).getId() + "</td>");
					sb.append("<td class=\"see\">" + "<input id=\"submit\" type=\"button\" value=\"복귀\" onClick=\"userRestart(\'" + listBean.get(i).getId() + "\')\" />" + "</td>");
					sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("<button id=\'list\' onClick=\'start()\'>" + "회원 리스트" + "</button>");
		return sb.toString();
	}

	//사용자 정지 버튼
	public ModelAndView userDelete(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		try {
			if(dao.userDelete(mb) == 1) {
				System.out.println("사용자 정지 성공");

				transaction = true;
				
				mav.addObject("MemberList", MemberList(mb));
				mav.addObject("type", session.getAttribute("type"));
				mav.setViewName("userBrackList");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		setTransactionResult(transaction);
		return mav;
	}
	
	//사용자 복귀 버튼
	public ModelAndView userrestart(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		try {
			if(dao.userRestart(mb) == 1) {
				System.out.println("사용자 복귀 성공");

				transaction = true;
				
				mav.addObject("MemberList", getstopList(mb));
				mav.addObject("type", session.getAttribute("type"));
				mav.setViewName("userBrackList");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		setTransactionResult(transaction);
		return mav;
	}
}