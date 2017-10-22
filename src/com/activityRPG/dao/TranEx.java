/**
 * 
 */
package com.activityRPG.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @클래스명 : TranEx
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
public class TranEx {
	@Autowired
	protected DataSourceTransactionManager tm;
	protected TransactionStatus status = null;

	// Transaction Definition
	protected void setTransactionConf(int propagationBehavior, int isolationLevel, boolean readOnly){
		// Transaction의 Propagation과 Isolation 지정
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// PropagationBehavior : propagation의 상수값
		def.setPropagationBehavior(propagationBehavior);
		// isolationLevel : Isolation의 상수값
		def.setIsolationLevel(isolationLevel);
		// readOnly : 읽기 전용에 대한 boolean
		def.setReadOnly(readOnly);

		status = 	tm.getTransaction(def);

	}
	// Transaction Result
	protected void setTransactionResult(boolean isResult) {
		if(isResult) {
			tm.commit(status);
		}else {
			tm.rollback(status);
		}
	}
}

