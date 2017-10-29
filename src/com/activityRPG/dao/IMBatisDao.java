/**
 * 
 */
package com.activityRPG.dao;

import java.util.List;
import java.util.Map;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.beans.MemberBean;

/**
 * @클래스명 : IMyBatis
 * @작성자 : 신태휘
 * @작성일 : 2017. 9. 28.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
public interface IMBatisDao {
	
	//login >> id/pwd check
	public int IdCheck(MemberBean mb);
	public MemberBean PwdCheck(MemberBean mb);
	
	//로그인 상태 저장
	public int AccessHistory(MemberBean mb);
	
	public int joinSuccess(MemberBean mb);

	public int idFind(MemberBean mb);
	public MemberBean idSend(MemberBean mb);
	
	public int mailcheck(MemberBean mb);
	public MemberBean mailSend(MemberBean mb);
	
	public int pwdUpdate(MemberBean mb);
	
	public int getRaspCheck(ActivityBean bean);			// 라즈베리파이 코드 유무 확인
	
	public int setRaspMem(ActivityBean bean);			// 라즈베리파이-회원 연동
	
	public BoardBean getCharaName(BoardBean bean);		// 캐릭터 이름 불러오기
	
	public GameBean getCharacterStatus(GameBean bean);	// 캐릭터 능력치 조회
	
	public List<GameBean> getEquipList(Map map);	// 캐릭터 장비 조회
	
	public List<GameBean> getIvenList(Map map);		// 캐릭터 소지품 조회
	
	public GameBean getItemInfo(Map map);		// 아이템 정보 조회
	
	public List<BoardBean> getGuildBoardList(BoardBean bean);		// 길드 보드의 리스트 불러오기
	
	public BoardBean getGuildBoardContent(BoardBean bean);	// 길드 보드 내용 불러오기
	
	public int setGuildBoardUpHit(BoardBean bean);					// 길드 보드 조회수 증가
	
	public int setGuildBoardWrite(BoardBean bean);					// 길드 보드 글 작성하기
	
	public int setGuildBoardReplyWrite(BoardBean bean);					// 길드 보드 답글 작성하기
	
	
	
	
	
	
}
