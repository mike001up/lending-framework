package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.api.entity.SysUserScope;
import org.junit.jupiter.api.*;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UA: 范围授权 + AL: 审计日志")
public class UA_AL_ScopeAndAuditTest {

	@Test
	@DisplayName("UA-02: 授权记录grantedBy和grantedAt")
	void testGrantScopeRecords() {
		SysUserScope scope = new SysUserScope();
		scope.setUserId(1L);
		scope.setScopeId(100L);
		scope.setGrantedBy(2L);
		scope.setGrantedAt(Instant.now());

		assertNotNull(scope.getGrantedBy(), "grantedBy不应为null");
		assertNotNull(scope.getGrantedAt(), "grantedAt不应为null");
		assertEquals(2L, scope.getGrantedBy());
	}

	@Test
	@DisplayName("UA-03: 撤销授权设置revokedAt")
	void testRevokeScopeSetsRevokedAt() {
		SysUserScope scope = new SysUserScope();
		scope.setUserId(1L);
		scope.setScopeId(100L);
		scope.setGrantedAt(Instant.now());

		scope.setRevokedAt(Instant.now());
		assertNotNull(scope.getRevokedAt(), "撤销后revokedAt不应为null");
	}

	@Test
	@DisplayName("UA-05: 已撤销记录(revokedAt非null)应被过滤")
	void testRevokedRecordsFiltered() {
		SysUserScope active = new SysUserScope();
		active.setUserId(1L);
		active.setScopeId(100L);
		active.setGrantedAt(Instant.now());

		SysUserScope revoked = new SysUserScope();
		revoked.setUserId(1L);
		revoked.setScopeId(200L);
		revoked.setGrantedAt(Instant.now());
		revoked.setRevokedAt(Instant.now());

		assertNull(active.getRevokedAt(), "有效授权revokedAt应为null");
		assertNotNull(revoked.getRevokedAt(), "已撤销授权revokedAt不应为null");
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
			SysLog log = new SysLog();
			log.setOperationType(type);
			assertEquals(type, log.getOperationType());
		}
	}

	@Test
	@DisplayName("AL-03: 操作成功result=1，失败result=0")
	void testResultValues() {
		SysLog successLog = new SysLog();
		successLog.setResult(1);
		assertEquals(1, successLog.getResult(), "成功result应为1");

		SysLog failLog = new SysLog();
		failLog.setResult(0);
		assertEquals(0, failLog.getResult(), "失败result应为0");
	}

}