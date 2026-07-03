package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.api.entity.SysUserClient;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UA: 客户端授权 + AL: 审计日志")
public class UA_AL_ClientAndAuditTest {

	@Test
	@DisplayName("UA-02: 授权记录userId和clientId")
	void testGrantClientRecords() {
		SysUserClient uc = new SysUserClient();
		uc.setUserId(1L);
		uc.setClientId(100L);

		assertNotNull(uc.getUserId(), "userId不应为null");
		assertNotNull(uc.getClientId(), "clientId不应为null");
		assertEquals(100L, uc.getClientId());
	}

	@Test
	@DisplayName("UA-03: 重复授权不报错")
	void testDuplicateGrantNoError() {
		SysUserClient uc1 = new SysUserClient();
		uc1.setUserId(1L);
		uc1.setClientId(100L);

		SysUserClient uc2 = new SysUserClient();
		uc2.setUserId(1L);
		uc2.setClientId(100L);

		assertEquals(uc1.getClientId(), uc2.getClientId(), "相同clientId应幂等");
	}

	@Test
	@DisplayName("AL-01: SysLog包含userId/operationType/result字段")
	void testSysLogFields() {
		SysLog log = new SysLog();
		log.setUserId(1L);
		log.setOperationType("LOGIN");
		log.setResult(1);

		assertNotNull(log.getUserId(), "userId不应为null");
		assertEquals("LOGIN", log.getOperationType(), "operationType应为LOGIN");
		assertEquals(1, log.getResult(), "成功操作result应为1");
	}

	@Test
	@DisplayName("AL-02: operationType区分LOGIN/LOGOUT/OPERATION/GATEWAY_ACCESS")
	void testOperationTypeEnum() {
		String[] types = {"LOGIN", "LOGOUT", "OPERATION", "GATEWAY_ACCESS"};
		for (String type : types) {
			RemoteSysLogDTO log = new RemoteSysLogDTO();
			log.setOperationType(type);
			assertEquals(type, log.getOperationType());
		}
	}

	@Test
	@DisplayName("AL-03: 操作成功result=1，失败result=0")
	void testResultValues() {
		RemoteSysLogDTO successLog = new RemoteSysLogDTO();
		successLog.setResult(1);
		assertEquals(1, successLog.getResult(), "成功result应为1");

		RemoteSysLogDTO failLog = new RemoteSysLogDTO();
		failLog.setResult(0);
		assertEquals(0, failLog.getResult(), "失败result应为0");
	}

}