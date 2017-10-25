/**
 * 
 */
package com.activityRPG.dao;

import java.util.List;
import java.util.Map;

import com.activityRPG.beans.ActivityBean;
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
	
	public int getRaspCheck(ActivityBean bean);			// 라즈베리파이 코드 유무 확인
	
	public int setRaspMem(ActivityBean bean);			// 라즈베리파이-회원 연동
	
	public GameBean getCharacterStatus(GameBean bean);	// 캐릭터 능력치 조회
	
	public List<GameBean> getEquipList(Map map);	// 캐릭터 장비 조회
	
	public List<GameBean> getIvenList(Map map);
}
