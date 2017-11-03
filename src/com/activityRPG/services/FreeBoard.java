/**
 * 
 */
package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
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
		//자유게시판 글 쓰기 페이지
		case 3:
			mav = freeboardinsertpage((BoardBean)object[0]);
			break;
		//자유게시판 글 등록
		case 4:
			mav = freeboardinsert((BoardBean)object[0]);
			break;
		//자유게시판 글 등록
		case 5:
			mav = freeboarddelete((BoardBean)object[0]);
			break;
		//자유게시판 글 등록
		case 6:
			mav = freeboardupdate((BoardBean)object[0]);
			break;
		//자유게시판 글 등록
		case 7:
			mav = freeupdatecheck((BoardBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//메인 게시판(자유)
	public ModelAndView freeboard(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println(session.getAttribute("id"));
		System.out.println(board.getId());
		
		System.out.println("service :: 메인 게시판(자유)");
		//board.setId(session.getAttribute("id").toString());
		try {
			mav.addObject("freelist", freeboardlist(board));
			mav.setViewName("freeBoardList");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//메인 게시판(자유) >> 리스트 
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
			sb.append("<td>" + "<button onClick=\"viewContents(\'"+ bean.get(i).getCode() +"\')\" >" + bean.get(i).getTitle() + "</button>" + "</td>");
			sb.append("<td>" + bean.get(i).getId() + "</td>");
			sb.append("<td>" + sdf.format(bean.get(i).getDate()) + "</td>");
			sb.append("<td>" + "<button onClick=\"freedelete(\'"+ bean.get(i).getCode() +"\', \'"+bean.get(i).getId()+"\')\">" + "삭제" + "</button>" + "</td>");
			sb.append("</tr>");
			sb.append("</br>");
		}
		return sb.toString();
	}
	
	//자유게시판 글 내용
	public ModelAndView freeboardcontent(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println(session.getAttribute("id"));
		System.out.println(board.getId());

		System.out.println("service :: 자유게시판 내용 보기");
		try {
			mav.addObject("freecontent", getfreeboardcontent(board));
			mav.setViewName("freeBoardContent");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//자유게시판 글 내용
	private String getfreeboardcontent(BoardBean board) throws Exception {
		StringBuffer sb = new StringBuffer();
		BoardBean bean = dao.freeBoardContent(board);
		
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "작성자" + "</td>");
		sb.append("<td>" + bean.getId() + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>" + "제목" + "</td>");
		sb.append("<td>" + bean.getTitle() + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>" + "내용" + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + bean.getContent() + "</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>" + "날짜" + "</td>");
		sb.append("<td>" + sdf.format(bean.getDate()) + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"글 수정하기\" onClick=\"freeUpdate(\'"+bean.getId()+"\',\'"+ bean.getCode() +"\',\'" + bean.getTitle() + "\',\'" + bean.getContent() + "\',\'" + sdf.format(bean.getDate()) +"\')\">");
		
		return sb.toString();
	}
	
	//자유게시판 글쓰기 페이지
	public ModelAndView freeboardinsertpage(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 자유게시판 글쓰기 페이지");
		System.out.println(session.getAttribute("id"));
		System.out.println(board.getId());
		try {
			if(session.getAttribute("id") != null) {
				mav.setViewName("freeInserPage");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//자유게시판 글 등록
	public ModelAndView freeboardinsert(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 글 등록하기");
		session.setAttribute("id", board.getId());
		System.out.println(session.getAttribute("id"));
		try {
			if(session.getAttribute("id") != null) {
				if(dao.freeInsert(board) != 0) {
					System.out.println("데이터베이스에 게시글 저장함.");
					mav.setViewName("freeBoardList");
					mav.addObject("freelist", freeboardlist(board));
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//자유게시판 글 삭제
	public ModelAndView freeboarddelete(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 글 삭제");
		System.out.println(session.getAttribute("id"));
		System.out.println(board.getId());
		try {
			if(board.getId().equals(session.getAttribute("id"))) {
				if(dao.freeDelete(board) != 0) {
					mav.setViewName("freeBoardList");
					mav.addObject("freelist", freeboardlist(board));
				}
			}else {
				mav.setViewName("freeBoardList");
				mav.addObject("freelist", freeboardlist(board));
				mav.addObject("message", "삭제하실 수 없는 게시글 입니다.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//자유게시판 글 수정 페이지
	public ModelAndView freeboardupdate(BoardBean board) throws Exception {
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = null;
		System.out.println("service :: 자유게시판 글 수정 페이지");
		//session.setAttribute("id", board.getId());
		System.out.println();
		System.out.println(session.getAttribute("id"));
		System.out.println(board.getId());
		try {
			if(board.getId().equals(session.getAttribute("id"))) {
				map = new HashMap<String, String>();
				map.put("title", board.getTitle());
				map.put("content", board.getContent());
				map.put("date", sdf.format(board.getDate()));
				map.put("id", board.getId());
				
				
				mav.setViewName("freeBoardUpdate");
				mav.addObject("title", map.get("title"));
				mav.addObject("content", map.get("content"));
				mav.addObject("date", map.get("date"));
				mav.addObject("id", map.get("id"));
				mav.addObject("code", board.getCode());
			}else {
				mav.addObject("freecontent", getfreeboardcontent(board));
				mav.setViewName("freeBoardContent");
				mav.addObject("message", "수정하실 수 없는 게시글 입니다.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//자유게시판 글 수정 올리기
		public ModelAndView freeupdatecheck(BoardBean board) throws Exception {
			ModelAndView mav = new ModelAndView();
			System.out.println("service :: 자유게시판 글 수정 올리기");
			//session.setAttribute("id", board.getId());
			try {
				if(board.getId().equals(session.getAttribute("id"))) {
					if(dao.freeUpdate(board) != 0) {
						mav.addObject("id", board.getId());
						mav.addObject("title", board.getTitle());
						mav.addObject("content", board.getContent());
						mav.setViewName("freeBoardList");
						mav.addObject("freelist", freeboardlist(board));
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return mav;
		}
}
