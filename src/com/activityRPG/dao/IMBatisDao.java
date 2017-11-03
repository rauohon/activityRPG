/**
 * 
 */
package com.activityRPG.dao;

import java.util.List;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.MemberBean;

/**
 * @클래스명 : IMyBatis
 * @작성자 : 신태휘
 * @작성일 : 2017. 9. 28.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
public interface IMBatisDao {
	
	public int IdCheck(MemberBean mb);
	public MemberBean PwdCheck(MemberBean mb);				//login >> id/pwd check
	
	public int AccessHistory(MemberBean mb);				//로그인 상태 저장
	
	public int joinSuccess(MemberBean mb);					//회원가입
	
	public int getRaspCheck(ActivityBean ab);				// 라즈베리파이 코드 유무 확인
	
	public int setRaspMem(ActivityBean ab);					// 라즈베리파이-회원 연동
	
	public int idFind(MemberBean mb);						//아이디 찾기 >> 핸드폰 번호로 확인
	public MemberBean idSend(MemberBean mb);				//핸드폰번호로 확인 한 아이디 보여줌
	
	public int mailcheck(MemberBean mb);					//이메일 확인 후
	public MemberBean mailSend(MemberBean mb);				//이메일 발송
	public int pwdUpdate(MemberBean mb);					//db에 임시비밀번호로 변경
	
	public MemberBean info(MemberBean mb);					//나의 정보 페이지에 정보 보여주기
	public int pwdChange(MemberBean mb);					//패스워드 변경
	
	public int nameUpdate (MemberBean mb);					//나의 정보 >> 이름 수정
	public int phoneUpdate (MemberBean mb);					//나의 정보 >> 핸드폰번호 수정
	public int mailUpdate (MemberBean mb);					//나의 정보 >> 이메일 수정
	
	public int writingMessage(MemberBean mb);				//메시지 보내기
	
	public List<MemberBean> getMessageList(MemberBean mb);	//받은 메시지 리스트
	public List<MemberBean> sendMessageList(MemberBean mb);	//보낸 메시지 리스트
	public int messageDelete(MemberBean mb);				//받은 메시지 삭제
	public int sendMessageDelete(MemberBean mb);				//보낸 메시지 삭제
	
	public int adminId(MemberBean mb);						//관리자 로그인 확인
	
	public List<BoardBean> freeBoardList(BoardBean board);	//자유게시판 리스트
	public BoardBean freeBoardContent(BoardBean board);		//자유게시판 내용 보기
	
	public int freeIdCheck(BoardBean board);				//자유게시판 글 쓰기 위한 아이디 체크
	public int freeInsert(BoardBean board);					//자유게시판 글 저장
	public int freeDelete(BoardBean board);					//자유게시판 글 삭제
	public int freeUpdate(BoardBean board);					//자유게시판 글 수정
}
