/**
 * 
 */
package com.activityRPG.dao;

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
}
