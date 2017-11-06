package com.activityRPG.dao;

import java.util.List;
import java.util.Map;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.beans.BattleBean;
import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.beans.MemberBean;

/**
 * @클래스명 : IMyBatis
 * @작성일 : 2017. 9. 28.
 * @설명 : 
 */
public interface IMBatisDao {

	/*↓ 전지원*/
	public int IdCheck(MemberBean mb);

	public MemberBean PwdCheck(MemberBean mb);						//login >> id/pwd check

	public int AccessHistory(MemberBean mb);						//로그인 상태 저장

	public int joinSuccess(MemberBean mb);							//회원가입

	public int idFind(MemberBean mb);								//아이디 찾기 >> 핸드폰 번호로 확인

	public MemberBean idSend(MemberBean mb);						//핸드폰번호로 확인 한 아이디 보여줌

	public int mailcheck(MemberBean mb);							//이메일 확인 후

	public MemberBean mailSend(MemberBean mb);						//이메일 발송

	public int pwdUpdate(MemberBean mb);							//db에 임시비밀번호로 변경

	public MemberBean info(MemberBean mb);							//나의 정보 페이지에 정보 보여주기

	public int pwdChange(MemberBean mb);							//패스워드 변경

	public int nameUpdate (MemberBean mb);							//나의 정보 >> 이름 수정

	public int phoneUpdate (MemberBean mb);							//나의 정보 >> 핸드폰번호 수정

	public int mailUpdate (MemberBean mb);							//나의 정보 >> 이메일 수정

	public int writingMessage(MemberBean mb);						//메시지 보내기

	public List<MemberBean> getMessageList(MemberBean mb);			//받은 메시지 리스트

	public List<MemberBean> sendMessageList(MemberBean mb);			//보낸 메시지 리스트

	public int messageDelete(MemberBean mb);						//받은 메시지 삭제

	public int sendMessageDelete(MemberBean mb);					//보낸 메시지 삭제

	public int adminId(MemberBean mb);								//관리자 로그인 확인

	public List<BoardBean> freeBoardList(BoardBean board);			//자유게시판 리스트

	public BoardBean freeBoardContent(BoardBean board);				//자유게시판 내용 보기

	public int freeIdCheck(BoardBean board);						//자유게시판 글 쓰기 위한 아이디 체크

	public int freeInsert(BoardBean board);							//자유게시판 글 저장

	public int freeDelete(BoardBean board);							//자유게시판 글 삭제

	public int freeUpdate(BoardBean board);							//자유게시판 글 수정

	public GameBean getCharacterStatus(GameBean bean);				// 캐릭터 능력치 조회

	public GameBean getEquipList(Map<String, String> map);			// 캐릭터 장비 조회

	public GameBean getItemName(Map<String, String> map);			// 캐릭터 장비 강화 레벨 조회

	public GameBean getEnLevel(Map<String, String> map);			// 캐릭터 장비 강화 레벨 조회

	public List<GameBean> getIvenList(Map<String, String> map);		// 캐릭터 소지품 조회
	/*↑ 전지원*/
	/*↓ 신태휘*/
	public int getRaspCheck(ActivityBean ab);						// 라즈베리파이 코드 유무 확인

	public int setRaspMem(ActivityBean ab);							// 라즈베리파이-회원 연동

	public GameBean getItemInfo(GameBean bean);						// 아이템 정보 조회

	public int getIsEquip(Map<String, String> map);					// 착용 아이템 사용전 기 착용여부 확인

	public int setEquipItemUpdate(Map<String, String> map);			// 착용 아이템 사용

	public int setAfterItemUse(Map<String, String> map);			// 소모 아이템 사용후 1개 감소 시키기

	public int setItemApplyStatus(GameBean bean);					// 아이템 사용후 캐릭터 능력치 업데이트

	public int setItemDisArm(Map<String, String> map);				// 아이템 장착 해제하기

	public int setItemDisArmStatus(GameBean bean);					// 아이템 사용후 캐릭터 능력치 업데이트

	public BoardBean getCharaName(BoardBean bean);					// 캐릭터 이름 불러오기

	public List<BoardBean> getGuildBoardList(BoardBean bean);		// 길드 보드의 리스트 불러오기

	public BoardBean getGuildBoardContent(BoardBean bean);			// 길드 보드 내용 불러오기

	public int setGuildBoardUpHit(BoardBean bean);					// 길드 보드 조회수 증가

	public int setGuildBoardWrite(BoardBean bean);					// 길드 보드 글 작성하기

	public int setGuildBoardRemove(BoardBean bean);					// 길드 보드 글 삭제 하기

	public int setGuildBoardModify(BoardBean bean);					// 길드 보드 글 수정 하기

	public List<BoardBean> getGuildBoardReply(BoardBean bean);		// 길드 댓글 불러오기

	public int setGuildBoardReply(BoardBean bean);					// 길드 보드 댓글 작성하기

	public int setGuildBoardReplyDelete(BoardBean bean);			// 길드 보드 댓글 삭제하기
	/*↑ 신태휘*/
	//************************************김훈**************************************
	//아이디 체크(중복)
	public int userIdCheck(MemberBean memberBean);

	//공지사항 리스트 출력
	public List<BoardBean> newsBoardList();

	//공지사항 작성
	public int newsBoardMake(BoardBean boardBean);

	//관리자 판단
	public int userType(MemberBean memberBean);

	//공지사항 게시글 내용 출력
	public BoardBean newsBoardContents(BoardBean boardBean);

	//공지사항 게시글 조회수 증가
	public int newsBoardHitUp(BoardBean boardBean);

	//공지사항 게시글 수정
	public int newsBoardModify(BoardBean boardBean);

	//공지사항 게시글 삭제
	public int newsBoardDelete(BoardBean boardBean);

	//공지사항 게시글 작성자 검색 출력
	public List<BoardBean> newsBoardSearchId(BoardBean boardBean);

	//공지사항 게시글 제목 검색 출력
	public List<BoardBean> newsBoardSearchTitle(BoardBean boardBean);

	//캐릭터 유무 검사
	public int characterIdCheck(GameBean gameBean);

	//캐릭터 성별 확인
	public int characterSex(MemberBean memberBean);

	//캐릭터 이름 유무 확인
	public int characterNameCkeck(GameBean gameBean);

	//캐릭터 생성
	public int characterCreate(GameBean gameBean);

	//캐릭터 스킬1 생성
	public int characterSkill1(GameBean gameBean);

	//캐릭터 스킬2 생성
	public int characterSkill2(GameBean gameBean);

	//캐릭터 스킬3 생성
	public int characterSkill3(GameBean gameBean);

	//캐릭터 스킬4 생성
	public int characterSkill4(GameBean gameBean);

	//캐릭터 이름 가져오기
	public String getCharacterName(String userId);

	//캐릭터 이름에 해당하는 인벤토리 가져오기
	public List<GameBean> getInventory(String characterName);

	//캐릭터 이름과 아이템 코드를 받아 선택한 아이템 정보 가져오기
	public List<GameBean> getChooseItem(GameBean gameBean);

	//강화 레벨에 해당하는 강화석, 확률, 증가량 가져오기
	public List<GameBean> getEnhanceLevel(int enhanceLevel);

	//소지한 강화석 개수 가져오기
	public int getMaterial(String characterName);

	//소지한 강화석 - 필요한 강화석
	public int updateMaterial(GameBean gameBean);

	//강화 등급 증가
	public int updateEnhanceLevel(GameBean gameBean);

	//인벤토리 소지한 아이템 개수 가져오기
	public int getInventoryItemAmount(GameBean gameBean);

	//소지한 아이템 개수 감소
	public int decreaseItem(GameBean gameBean);

	//소지한 아이템 삭제
	public int deleteItem(GameBean gameBean);

	//캐릭터 정보 가져오기
	public List<GameBean> characterInformation(String characterName);

	//몬스터 정보 가져오기
	public List<BattleBean> monsterInformation(int monsterCode);

	//캐릭터 스킬 가져오기
	public List<GameBean> characterSkillMenu(String characterName);

	//해당 캐릭터가 사용하는 스킬 정보
	public List<GameBean> skillInformation(GameBean gameBean);

	//몬스터 스킬 정보
	public List<BattleBean> monsterSkill(BattleBean battleBean);

	//캐릭터 골드 변화
	public int goldChange(GameBean gameBean);

	//캐릭터 경험치 증가
	public int characterExp(GameBean gameBean);

	//인벤토리에 아이템 존재 유무 확인
	public int inventoryItemCheck(GameBean gameBean);

	//인벤토리 아이템 추가
	public int itemInsert(GameBean gameBean);

	//인벤토리 아이템 개수 업데이트
	public int itemAmountInsert(GameBean gameBean);

	//레벨 업
	public int levelUp(GameBean gameBean);

	//현재 수락한 퀘스트 
	public int myQuestCheck(GameBean gameBean); 

	//퀘스트 완료 코드 수정
	public int myQuestSuccess(GameBean gameBean);

	//************************************김훈**************************************


}

