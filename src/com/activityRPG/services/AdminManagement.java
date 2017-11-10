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
			//관리자 메인 게시판(자유)
		case 2:
			mav = adminfreeboard((MemberBean)object[0]);
			break;
		case 3:
			mav = userDelete((MemberBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//관리자 메인 페이지
	public ModelAndView adminmain(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		List<MemberBean> ListMemberBean = null;
		String MemberList = null;

		System.out.println("service :: 관리자 메인 페이지");
		System.out.println(mb.getId());
		try {
			StringBuffer sb = new StringBuffer();
			ListMemberBean = dao.MemberList();
			if(dao.IdCheck(mb) != 0) {
				//mb.setId(session.getAttribute("id").toString());
				//종
				int z = 0;
				for(int i = 0 ; i <= ListMemberBean.size()/15; i++) {
					sb.append("<table border=1>");
					sb.append("<tr>");
					sb.append("<th id=\"uid\">아이디</th>");
					sb.append("<th id=\"bar\">회원활동</th>");

					sb.append("</tr>");
					for(int j = 0 + z; j < 15*(i+1); j++) {
						if(j < ListMemberBean.size()) {
							mb = ListMemberBean.get(j);
							sb.append("<tr>");
							sb.append("<td id=\"user\">" + mb.getId() + "</td>");
							sb.append("<td class=\"see\">" + "<input id=\"submit\" type=\"button\" value=\"정지\" onClick=\"userDelete(\'" + mb.getId() + "\')\" />" + "</td>");
							sb.append("</tr>");
						}else {
							break;
						}
						z = j + 1;
					}
					sb.append("</table>");
				}
				MemberList = sb.toString();
				mav.addObject("listSize", ListMemberBean.size()/15);
				mav.addObject("MemberList", MemberList);
				mav.addObject("type", session.getAttribute("type"));
				//종
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
	//유저 정지
	public ModelAndView userDelete(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		List<MemberBean> ListMemberBean = null;
		String MemberList = null;
		boolean transaction = false;

		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
			System.out.println(mb.getId());
			if(dao.userDelete(mb) == 1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			transaction = true;
			setTransactionResult(transaction);

			StringBuffer sb = new StringBuffer();
			ListMemberBean = dao.MemberList();
			int z = 0;
			for(int i = 0 ; i <= ListMemberBean.size()/15; i++) {
				sb.append("<table border=1>");
				sb.append("<tr>");
				sb.append("<th id=\"uid\">아이디</th>");
				sb.append("<th id=\"bar\">회원활동</th>");
				sb.append("</tr>");
				for(int j = 0 + z; j < 15*(i+1); j++) {
					if(j < ListMemberBean.size()) {
						mb = ListMemberBean.get(j);
						sb.append("<tr>");
						sb.append("<td id=\"user\">" + mb.getId() + "</td>");
						sb.append("<td class=\"see\">" + "<input id=\"submit\" type=\"button\" value=\"정지\" onClick=\"userDelete(\'" + mb.getId() + "\')\" />" + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
			}
			MemberList = sb.toString();
			mav.addObject("listSize", ListMemberBean.size()/15);
			mav.addObject("MemberList", MemberList);
			mav.addObject("type", session.getAttribute("type"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("adminmain");

		return mav;
	}
}