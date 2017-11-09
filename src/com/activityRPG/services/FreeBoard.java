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
 * @작성자 : 전지원
 * @작성일 : 2017. 10. 14.
 * @설명 : 
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

	public ModelAndView entrance(int code, Object...object) {
		ModelAndView mav = null;
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
			//자유게시판 댓글 달기
		case 8:
			mav = freecomment((BoardBean)object[0]);
			break;
			//자유 게시판 글 제목 찾기
		case 9:
			mav = freetitlefine((BoardBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//메인 게시판(자유)
	public ModelAndView freeboard(BoardBean board) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 메인 게시판(자유)");
		try {
			mav.addObject("id", board.getId());
			mav.addObject("code", board.getCode());
			mav.addObject("freelist", freeboardlist(board));
			mav.setViewName("freeBoardList");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//메인 게시판(자유) >> 리스트 
	private String freeboardlist(BoardBean board) {
		StringBuffer sb = new StringBuffer();
		List<BoardBean> bean = dao.freeBoardList(board);

		sb.append("<table class=\'type10\'>");
		sb.append("<thead>");
		sb.append("<tr>");
		sb.append("<th>" + "번호" + "</th>");
		sb.append("<th>" + "제목" + "</th>");
		sb.append("<th>" + "작성자" + "</th>");
		sb.append("<th>" + "날짜" + "</th>");
		sb.append("<th>" + "</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		
		for(int i=0; i<bean.size(); i++) {
			sb.append("<tbody>");
			sb.append("<tr>");
			sb.append("<td>" + bean.get(i).getCode() + "</td>");
			sb.append("<td>" + "<button id=\'title\' onClick=\"viewContents(\'"+ bean.get(i).getCode() +"\')\" >" + bean.get(i).getTitle() + "</button>" + "</td>");
			sb.append("<td>" + bean.get(i).getId() + "</td>");
			sb.append("<td>" + sdf.format(bean.get(i).getDate()) + "</td>");
			sb.append("<td>" + "<button id=\'del\' onClick=\"freedelete(\'"+ bean.get(i).getCode() +"\', \'"+bean.get(i).getId()+"\')\">" + "삭제" + "</button>" + "</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
		}
		return sb.toString();
	}

	//자유게시판 글 내용
	public ModelAndView freeboardcontent(BoardBean board) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 자유게시판 내용 보기");
		try {
			mav.addObject("id", board.getId());
			mav.addObject("freecontent", getfreeboardcontent(board));
			mav.setViewName("freeBoardContent");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//자유게시판 글 내용
	private String getfreeboardcontent(BoardBean board) {
		StringBuffer sb = new StringBuffer();
		BoardBean bean = dao.freeBoardContent(board);
		List<BoardBean> beans = dao.getfreeComment(board);

		sb.append("<table class=\'pop_table\'>");
		sb.append("<caption>" + "자유게시판 상세보기" + "</caption>");
		sb.append("<tr>");
		sb.append("<th scope=\'row\'>" + "제목" + "</th>");
		sb.append("<td>" + bean.getTitle() + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<th scope=\'row\'>" + "작성자" + "</th>");
		sb.append("<td>" + bean.getId() + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<th scope=\'row\'>" + "내용" + "</th>");
		sb.append("<td>" + bean.getContent() + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<th scope=\'row\'>" + "날짜" + "</th>");
		sb.append("<td>" + sdf.format(bean.getDate()) + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" id=\'update\' value=\"글 수정하기\" onClick=\"freeUpdate(\'"+bean.getId()+"\',\'"+ bean.getCode() +"\',\'" + bean.getTitle() + "\',\'" + bean.getContent() + "\',\'" + sdf.format(bean.getDate()) +"\')\">");
		sb.append("<input type=\"button\" id=\'lists\' value=\"목록\" onClick=\"freeUpdateBack(\'"+bean.getId()+"\',\'"+ bean.getCode() +"\',\'" + bean.getTitle() + "\',\'" + sdf.format(bean.getDate()) +"\')\">");
		
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th style=\'padding-right: 15px;\'>" + "댓글쓰기" + "</th>");
		sb.append("<th>" + "<input type=\'text\' id=\'comment\' name=\'comment\' style=\'padding-right: 15px;\' />" + "</th>");
		sb.append("<th>" + "<button id=\'commentinput\' onClick=\"freeComment(\'"+ bean.getCode() +"\', \'"+ bean.getId() +"\')\">" + "댓글 달기" + "</button>" + "</th>");
		sb.append("</tr>");
		
		for(int i=0; i<beans.size(); i++) {
			sb.append("<tbody>");
			sb.append("<tr>");
			sb.append("<td>" + "작성자: " + beans.get(i).getId() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>" + beans.get(i).getComment() + "</td>");
			sb.append("<td>" + sdf.format(beans.get(i).getDate()) + "</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
		}
		return sb.toString();
	}
	
	//자유게시판 댓글 달기
	public ModelAndView freecomment(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 댓글 달기");
		try {
			board.setId(session.getAttribute("id").toString());
			if(session.getAttribute("id") != null) {
				if(dao.freeComment(board) != 0) {
					System.out.println("아이디 체크 완료");
					System.out.println("댓글 등록 완료");
					mav = freeboardcontent(board);
				}
			}else {
				mav = freeboardcontent(board);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//자유 게시판 글 제목 찾기
	public ModelAndView freetitlefine(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유 게시판 글 제목 찾기");
		try {
			if(dao.freeTitlefine(board) != 0) {
				System.out.println(board.getType());
				if(board.getType().equals("freetitle")) {
					mav.addObject("freelist", getfreetitlefine(board));
					mav.setViewName("freeBoardList");
				}if(board.getType().equals("freeuser")) {
					mav.addObject("freelist", getfreetitlefine(board));
					mav.setViewName("freeBoardList");
				}
			}else {
				System.out.println("아님 죽었니ㅠㅠㅠ");
				mav.addObject("freelist", freeboardlist(board));
				mav.setViewName("freeBoardList");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//메인 게시판(자유) >> 리스트 
		private String getfreetitlefine(BoardBean board) {
			StringBuffer sb = new StringBuffer();
			List<BoardBean> bean = dao.freeTitleComment(board);

			sb.append("<table class=\'type10\'>");
			sb.append("<thead>");
			sb.append("<tr>");
			sb.append("<th>" + "번호" + "</th>");
			sb.append("<th>" + "제목" + "</th>");
			sb.append("<th>" + "작성자" + "</th>");
			sb.append("<th>" + "날짜" + "</th>");
			sb.append("<th>" + "</th>");
			sb.append("</tr>");
			sb.append("</thead>");
			
			for(int i=0; i<bean.size(); i++) {
				sb.append("<tbody>");
				sb.append("<tr>");
				sb.append("<td>" + bean.get(i).getCode() + "</td>");
				sb.append("<td>" + "<button id=\'title\' onClick=\"viewContents(\'"+ bean.get(i).getCode() +"\')\" >" + bean.get(i).getTitle() + "</button>" + "</td>");
				sb.append("<td>" + bean.get(i).getId() + "</td>");
				sb.append("<td>" + sdf.format(bean.get(i).getDate()) + "</td>");
				sb.append("<td>" + "<button id=\'del\' onClick=\"freedelete(\'"+ bean.get(i).getCode() +"\', \'"+bean.get(i).getId()+"\')\">" + "삭제" + "</button>" + "</td>");
				sb.append("</tr>");
				sb.append("</tbody>");
			}
			return sb.toString();
		}
		
	//자유게시판 글쓰기 페이지
	public ModelAndView freeboardinsertpage(BoardBean board) {
		ModelAndView mav = new ModelAndView();

		System.out.println(board.getId());
		System.out.println("service :: 자유게시판 글쓰기 페이지");
		try {
			System.out.println(session.getAttribute("id"));
			if(session.getAttribute("id") != null) {
				mav.setViewName("freeInserPage");
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해 주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//자유게시판 글 등록
	public ModelAndView freeboardinsert(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 글 등록하기");

		try {
			//session.setAttribute("id", board.getId());
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
	public ModelAndView freeboarddelete(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 글 삭제");
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
	public ModelAndView freeboardupdate(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = null;
		System.out.println("service :: 자유게시판 글 수정 페이지");
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
	public ModelAndView freeupdatecheck(BoardBean board) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 자유게시판 글 수정 올리기");
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
