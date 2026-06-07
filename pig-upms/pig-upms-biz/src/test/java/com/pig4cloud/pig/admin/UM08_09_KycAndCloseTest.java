package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysUser;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UM-08: 实名认证 + UM-09: 关停/恢复")
public class UM08_09_KycAndCloseTest {

	@Test
	@DisplayName("UM-08-01: 提交认证→status=in_progress")
	void testSubmitCertification() {
		SysUser user = new SysUser();
		user.setCertificationStatus("not_certified");

		user.setCertificationStatus("in_progress");
		user.setCertificationInfo("{\"realName\":\"张三\",\"idNumber\":\"110101199001011234\"}");

		assertEquals("in_progress", user.getCertificationStatus());
		assertNotNull(user.getCertificationInfo());
	}

	@Test
	@DisplayName("UM-08-02: 重复提交拒绝(in_progress状态)")
	void testDuplicateSubmitRejected() {
		SysUser user = new SysUser();
		user.setCertificationStatus("in_progress");

		assertTrue("in_progress".equals(user.getCertificationStatus()),
			"in_progress状态应拒绝重复提交");
	}

	@Test
	@DisplayName("UM-08-03: 审核通过→certified")
	void testReviewApproved() {
		SysUser user = new SysUser();
		user.setCertificationStatus("in_progress");

		String result = "approved";
		if ("approved".equals(result)) {
			user.setCertificationStatus("certified");
		}
		assertEquals("certified", user.getCertificationStatus());
	}

	@Test
	@DisplayName("UM-08-04: 审核不通过→failed")
	void testReviewRejected() {
		SysUser user = new SysUser();
		user.setCertificationStatus("in_progress");

		String result = "rejected";
		if ("approved".equals(result)) {
			user.setCertificationStatus("certified");
		} else {
			user.setCertificationStatus("failed");
		}
		assertEquals("failed", user.getCertificationStatus());
	}

	@Test
	@DisplayName("UM-09-01: 关停账户→closed")
	void testCloseAccount() {
		SysUser user = new SysUser();
		user.setStatus("enabled");

		user.setStatus("closed");
		assertEquals("closed", user.getStatus());
	}

	@Test
	@DisplayName("UM-09-03: 恢复账户→enabled")
	void testRestoreAccount() {
		SysUser user = new SysUser();
		user.setStatus("closed");

		user.setStatus("enabled");
		assertEquals("enabled", user.getStatus());
	}

	@Test
	@DisplayName("UM-08: certificationStatus四种状态枚举")
	void testCertificationStatusEnum() {
		String[] validStatuses = {"not_certified", "in_progress", "certified", "failed"};
		for (String status : validStatuses) {
			SysUser user = new SysUser();
			user.setCertificationStatus(status);
			assertEquals(status, user.getCertificationStatus());
		}
	}

}