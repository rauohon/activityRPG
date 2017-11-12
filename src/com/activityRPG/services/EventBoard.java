package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.MemberBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : EventBoard
 * @작성자 : 
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class EventBoard {
   
   @Autowired
   private Encryption enc;
   @Autowired
   private ProjectUtils session;
   @Autowired
   private IMBatisDao dao;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
   private MemberBean mb;

   public ModelAndView entrance(int code, Object...object) {
      ModelAndView mav = null;
      switch(code) {
      //이벤트 게시판 리스트
      case 1:
         mav = eventboard((BoardBean)object[0]);
         break;
         //이벤트 게시판 글 쓰기
      case 2:
    	  mav = eventboardwrite((BoardBean)object[0]);
    	  break;
          //이벤트 게시판 글 등록
       case 3:
     	  mav = eventinsert((BoardBean)object[0]);
     	  break;
     	  //이벤트 게시판 글 상세보기
       case 4:
     	  mav = eventBoardContent((BoardBean)object[0]);
     	 break;
    	  //이벤트 게시판 글 상세보기 >> 목록
      }
      System.out.println(mav.getViewName() + " : 스위치 mav 네임");
      return mav;
   }
   
   //이벤트 게시판 리스트
   public ModelAndView eventboard(BoardBean board) {
	   ModelAndView mav = new ModelAndView();

	   System.out.println("service :: 이벤트 게시판 리스트");
	   try {
		   board.setId(session.getAttribute("id").toString());
		   mav.addObject("eventList", eventboardlist(board));
		   mav.addObject("id", board.getId());
		   mav.setViewName("eventBoard");
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }
	   return mav;
   }
   //이벤트 게시판 >> 리스트 
   private String eventboardlist(BoardBean board) {
      StringBuffer sb = new StringBuffer();
      List<BoardBean> bean = dao.eventBoardList(board);

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
   
   //이벤트 게시판 글 작성
   public ModelAndView eventboardwrite(BoardBean board) {
	   ModelAndView mav = new ModelAndView();
	   mb = new MemberBean();

	   System.out.println("service :: 이벤트 게시판 글 작성");
	   try {
		   mb.setId(board.getId());
		   if(dao.adminId(mb) != 0) {
			   mav.setViewName("eventBoardWrite");
		   }
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }
	   return mav;
   }   
   
   //이벤트 게시판 글 등록
   public ModelAndView eventinsert(BoardBean board) {
	   ModelAndView mav = new ModelAndView();
	   mb = new MemberBean();

	   System.out.println("service :: 이벤트 게시판 글 등록");
	   try {
		   mb.setId(board.getId());
		   if(dao.adminId(mb) != 0) {
			   if(dao.eventInsert(board) != 0) {
				   System.out.println("데이터베이스에 게시글 저장함.");
				   mav.setViewName("eventBoard");
				   mav.addObject("eventList", eventboardlist(board));
			   }
		   }
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }
	   return mav;
   }
   
   //이벤트 게시판 글 상세보기
   public ModelAndView eventBoardContent(BoardBean board) {
	   ModelAndView mav = new ModelAndView();
	   mb = new MemberBean();

	   System.out.println("service :: 이벤트 게시판 글 상세보기");
	   try {
		   	mav.addObject("id", board.getId());
			mav.addObject("eventcontent", geteventboardcontent(board));
			mav.setViewName("eventBoardContent");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//자유게시판 글 내용
	private String geteventboardcontent(BoardBean board) {
		StringBuffer sb = new StringBuffer();
		BoardBean bean = dao.eventBoardContent(board);
		/*List<BoardBean> beans = dao.getfreeComment(board);*/

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
		sb.append("<input type=\"button\" id=\'lists\' value=\"목록\" onClick=\"eventUpdateBack(\'"+bean.getId()+"\',\'"+ bean.getCode() +"\',\'" + bean.getTitle() + "\',\'" + sdf.format(bean.getDate()) +"\')\">");
		
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th style=\'padding-right: 15px;\'>" + "댓글쓰기" + "</th>");
		sb.append("<th>" + "<input type=\'text\' id=\'comment\' name=\'comment\' style=\'padding-right: 15px;\' />" + "</th>");
		sb.append("<th>" + "<button id=\'commentinput\' onClick=\"freeComment(\'"+ bean.getCode() +"\', \'"+ bean.getId() +"\')\">" + "댓글 달기" + "</button>" + "</th>");
		sb.append("</tr>");
		
		/*for(int i=0; i<beans.size(); i++) {
			sb.append("<tbody>");
			sb.append("<tr>");
			sb.append("<td>" + "작성자: " + beans.get(i).getId() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>" + beans.get(i).getComment() + "</td>");
			sb.append("<td>" + sdf.format(beans.get(i).getDate()) + "</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
		}*/
		return sb.toString();
	}
}