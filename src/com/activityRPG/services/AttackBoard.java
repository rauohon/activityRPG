/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;
import com.activityRPG.beans.MemberBean;

/**
 * @클래스명 : AttackBoard
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class AttackBoard {
	@Autowired
	private Encryption Encryption;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private IMBatisDao IMBatisDao;
	
	/*Object...object >> Object 개체를 배열로 받는다. */
	
	
}
