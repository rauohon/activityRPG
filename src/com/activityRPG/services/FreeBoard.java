/**
 * 
 */
package com.activityRPG.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : FreeBoard
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class FreeBoard extends TranEx {
	
	@Autowired
	private Encryption enc;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private IMBatisDao dao;
	
	public ModelAndView entrance(int code, Object...object) throws Exception{
		ModelAndView mav = null;
		//session.setAttribute("page",((BoardBean)object[0]).getPage());
		switch(code) {
		//메인 게시판(자유)
		case 1:
			mav = freeboard((BoardBean)object[0]);
			break;
		//자유게시판 내용 보기
		case 2:
			mav = freeboardcontent((BoardBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//메인 게시판(자유)
	public ModelAndView freeboard(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 메인 게시판(자유)");
		/*board.setContent(dao.freeBoardtitle(board));*/
		board.setId(session.getAttribute("id").toString());
		try {
			mav.addObject("freelist", freeboardlist(board));
			mav.setViewName("freeBoardList");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//메인 게시판(자유) 리스트 
	private String freeboardlist(BoardBean board) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<BoardBean> bean = dao.freeBoardList(board);
		
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "번호" + "</td>");
		sb.append("<td>" + "제목" + "</td>");
		sb.append("<td>" + "작성자" + "</td>");
		sb.append("<td>" + "날짜" + "</td>");
		sb.append("</tr>");

		for(int i=0; i<bean.size(); i++) {
			
			sb.append("<tr>");
			sb.append("<td>" + bean.get(i).getCode() + "</td>");
			//sb.append("<td>" + "<button onClick=\"viewContents(\'"+ bean.get(i).getTitle() +"\', "+" \'"+ bean.get(i).getDate() +"\')\" />" + bean.get(i).getTitle() + "</button>" + "</td>");
			sb.append("<td>" + "<button onClick=\"viewContents(\'"+ bean.get(i).getTitle() +"\')\" />" + bean.get(i).getTitle() + "</button>" + "</td>");
			sb.append("<td>" + bean.get(i).getId() + "</td>");
					
			sb.append("<td>" + bean.get(i).getDate() + "</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	
	//자유게시판 내용 보기
	public ModelAndView freeboardcontent(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 자유게시판 내용 보기");

		try {
			mav.addObject("freecontent", getfreeboardcontent(board));
			mav.setViewName("freeBoardContent");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//자유게시판 내용
	private String getfreeboardcontent(BoardBean board) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<BoardBean> bean = dao.freeBoardContent(board);
		
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "작성자" + "</td>");
		sb.append("<td>" + "제목" + "</td>");
		sb.append("<td>" + "내용" + "</td>");
		sb.append("<td>" + "날짜" + "</td>");
		sb.append("</tr>");

		for(int i=0; i<bean.size(); i++) {
			sb.append("<tr>");
			sb.append("<td>" + bean.get(i).getId() + "</td>");
			sb.append("<td>" + bean.get(i).getTitle() + "</td>");
			sb.append("<td>" + bean.get(i).getContent() + "</td>");
			sb.append("<td>" + bean.get(i).getDate() + "</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	
}
