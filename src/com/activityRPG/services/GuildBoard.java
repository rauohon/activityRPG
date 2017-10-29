/**
 * 
 */
package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.utils.ProjectUtils;


/**
 * @클래스명 : GuildBoard
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class GuildBoard {
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	
	/**
	 * 처리내용 : 
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {
		
		switch(i) {
		
			case 0:
				mav = guildBoardPage((BoardBean)bean);
				break;
			
			case 1:
				mav = writeGBoardPage((BoardBean)bean);
				break;
				
			case 2:
				mav = writeGBoard((BoardBean)bean);
				break;
				
			case 3:
				mav = readGBoardPage((BoardBean)bean);
				break;
			
			case 4:
				mav = confirmDeleteGBoardPage((BoardBean)bean);
				break;
			
			case 5:
				mav = confirmModifyGBoardPage((BoardBean)bean);
				break;
			
			case 6:
				mav = deleteGBoard((BoardBean)bean);
				break;
				
			case 7:
				mav = modifyGBoardPage((BoardBean)bean);
				break;
			
			case 8:
				mav = replyGBoardPage((BoardBean)bean);
				break;
			
			case 9:
				mav = replyGBoard((BoardBean)bean);
				break;
		}		
		
		return mav;
	}
	
	/**
	 * 처리내용 : 답글 작성
	 * 작성일 : 2017. 10. 27.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView replyGBoard(BoardBean bean) {
		
		try {
			bean.setId(session.getAttribute("id").toString());
			bean.setGbTitle("└"+bean.getGbTitle());
			if(dao.setGuildBoardReplyWrite(bean) !=0) {
				RedirectView rv = null;
				rv = new RedirectView("/GuildBoardPage");
				rv.setExposeModelAttributes(false);
				mav.setView(rv);
			}else {
				mav.addObject("msg","system error");
				mav.setViewName("guildBoard");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}

	/**
	 * 처리내용 : 8-1. 답글 작성에서 원글 내용 불러오기
	 * 작성일 : 2017. 10. 27.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoardRead
	 * @return type : Map<String,String>
	 */
	private Map<String, String> replyGBoardRead(BoardBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		bean = dao.getGuildBoardContent(bean);
		
		try {
			map.put("writer", session.getAttribute("chName").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("title", bean.getGbTitle());
		map.put("reply", "-----------------------------");
		map.put("content", bean.getGbContent());
		map.put("wdate", sdf.format(bean.getGbWDate()));
		map.put("gbGroup",String.valueOf(bean.getGbGroup()));
		map.put("gbStep", String.valueOf(bean.getGbStep()));
		map.put("gbIndent", String.valueOf(bean.getGbIndent()));
		
		return map;
	}

	/**
	 * 처리내용 : 8. replyGBoardPage 연결
	 * 작성일 : 2017. 10. 27.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView replyGBoardPage(BoardBean bean) {
		
		Map<String, String> map = replyGBoardRead(bean);
		
		mav.addObject("action","ReplyGBoard");
		mav.addObject("writer", map.get("writer"));
		mav.addObject("title",map.get("title"));
		mav.addObject("reply",map.get("reply"));
		mav.addObject("content",map.get("content"));
		mav.addObject("hit",map.get("hit"));
		mav.addObject("gbGroup",map.get("gbGroup"));
		mav.addObject("gbStep",map.get("gbStep"));
		mav.addObject("gbIndent",map.get("gbIndent"));
		
		
		mav.setViewName("gBoardWriteReply");
		
		return mav;
	}


	/**
	 * 처리내용 : 7. modifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : modifyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView modifyGBoardPage(BoardBean bean) {
		
		mav.setViewName("gBoardWrite");
		
		return mav;
	}

	/**
	 * 처리내용 : 6. 게시글 삭제
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : deleteGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView deleteGBoard(BoardBean bean) {
		
		mav.setViewName("guildBoard");
		
		return mav;
	}	

	/**
	 * 처리내용 : 5. 수정전 확인(비밀번호) 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : confirmModifyGBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView confirmModifyGBoardPage(BoardBean bean) {
		
		mav.setViewName("gBoardConfirm");
		
		return mav;
	}

	/**
	 * 처리내용 : 4. 삭제전 확인(비밀번호) 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : deleteGBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView confirmDeleteGBoardPage(BoardBean bean) {
		
		mav.setViewName("gBoardConfirm");
		
		return mav;
	}
	
	/**
	 * 처리내용 : 3-1. 게시글 내용 출력
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	private Map<String, String> readGBoard(BoardBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		bean = dao.getGuildBoardContent(bean);
		
		map.put("writer", bean.getChName());
		map.put("title", bean.getGbTitle());
		map.put("content", bean.getGbContent());
		map.put("wdate", sdf.format(bean.getGbWDate()));
		map.put("hit", String.valueOf(bean.getGbHit()));
		map.put("gbCode",String.valueOf(bean.getGbCode()));
		
		return map;
	}

	/**
	 * 처리내용 : 3. 게시글 읽기 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView readGBoardPage(BoardBean bean) {
		Map<String, String> map = readGBoard(bean);
		if(dao.setGuildBoardUpHit(bean) != 0) {
			mav.addObject("writer", map.get("writer"));
			mav.addObject("title", map.get("title"));
			mav.addObject("content", map.get("content"));
			mav.addObject("wdate", map.get("wdate"));
			mav.addObject("hit", map.get("hit"));
			mav.addObject("gbCode",map.get("gbCode"));
			
			mav.setViewName("gBoardRead");
		}else {
			RedirectView rv = null;
			rv = new RedirectView("/GuildBoardPage");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}
		return mav;
	}
	
	/**
	 * 처리내용 : 2. 게시글 작성
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView writeGBoard(BoardBean bean) {
		
		try {
			System.out.println("안오냐?");
			bean.setId(session.getAttribute("id").toString());
			if(dao.setGuildBoardWrite(bean) !=0) {
				RedirectView rv = null;
				rv = new RedirectView("/GuildBoardPage");
				rv.setExposeModelAttributes(false);
				mav.setView(rv);
			}else {
				mav.addObject("msg","system error");
				mav.setViewName("guildBoard");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 처리내용 : 1. 게시글 작성 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView writeGBoardPage(BoardBean bean) {
			
			mav.addObject("action","WriteGBoard");
			mav.setViewName("gBoardWrite");
				
		return mav;
	}
	
	/**
	 * 처리내용 : 0-1 게시글 리스트 출력
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : guildBoardList
	 * @return type : String
	 */
	private String guildBoardList(BoardBean bean) {

		StringBuffer sb = new StringBuffer();
		List<BoardBean> gBoardList = dao.getGuildBoardList(bean);
		sb.append("<table><tr><th>글 번호</th><th>작성자</th><th>글 제목</th><th>작성일</th><th>조회수</th></tr>");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		for(int i=0 ; i< gBoardList.size();i++) {
			Collections.sort(gBoardList, new Comparator<BoardBean>(){
				@Override
				public int compare(BoardBean r2, BoardBean r1){
					return r1.getGbWDate().compareTo(r2.getGbWDate());
				}
			});
			System.out.println(gBoardList.get(i).getGbCode());
			sb.append("<tr><td>");
			sb.append(gBoardList.get(i).getGbCode());
			sb.append("</td><td>");
			sb.append(gBoardList.get(i).getChName());
			sb.append("</td><td onClick='createForm(\""+ gBoardList.get(i).getGbCode() +"\")'>");
			sb.append(gBoardList.get(i).getGbTitle());
			sb.append("</td><td>");
			sb.append(sdf.format((gBoardList.get(i).getGbWDate())));
			sb.append("</td><td>");
			sb.append(gBoardList.get(i).getGbHit());
			sb.append("</td></tr>");
		}

		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 0. 보드 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * @Method Name : guildBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView guildBoardPage(BoardBean bean) {
		// 로그인 여부 확인
		try {
			if(session.getAttribute("id") != null) {
				bean.setId(session.getAttribute("id").toString());
				bean = dao.getCharaName(bean);
				session.setAttribute("chName", bean.getChName());
				mav.addObject("boards", guildBoardList(bean));
				mav.setViewName("guildBoard");
				session.setAttribute("page", "guildBoard");
			}else {
				mav.setViewName("home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg","system error");
			mav.setViewName("home");
		}
		return mav;
	}

}
