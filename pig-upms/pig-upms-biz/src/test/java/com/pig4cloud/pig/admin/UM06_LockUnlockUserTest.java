package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.enums.LockFlagEnum;

import org.junit.jupiter.api.*;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UM-06: 登录锁定/解锁")
public class UM06_LockUnlockUserTest {

	@Test
	@DisplayName("UM-06-01: 锁定用户应设置lockFlag+lockUntil")
	void testLockUser() {
		SysUser user = new SysUser();
		user.setLockFlag(LockFlagEnum.NORMAL);

		user.setLockFlag(LockFlagEnum.LOCKED);
		user.setLockUntil(Instant.now().plusSeconds(900));

		assertEquals(CommonConstants.STATUS_LOCK, user.getLockFlag(), "lockFlag应为1");
		assertNotNull(user.getLockUntil(), "lockUntil不应为null");
		assertTrue(user.getLockUntil().isAfter(Instant.now()), "lockUntil应在未来");
	}

	@Test
	@DisplayName("UM-06-03: 解锁用户应清除lockFlag+lockUntil")
	void testUnlockUser() {
		SysUser user = new SysUser();
		user.setLockFlag(LockFlagEnum.LOCKED);
		user.setLockUntil(Instant.now().plusSeconds(600));

		user.setLockFlag(LockFlagEnum.NORMAL);
		user.setLockUntil(null);

		assertEquals(CommonConstants.STATUS_NORMAL, user.getLockFlag(), "lockFlag应为0");
		assertNull(user.getLockUntil(), "lockUntil应为null");
	}

	@Test
	@DisplayName("UM-06-04: lockUntil超时应视为未锁定")
	void testLockUntilExpiredAutoUnlock() {
		SysUser user = new SysUser();
		user.setLockFlag(LockFlagEnum.LOCKED);
		user.setLockUntil(Instant.now().minusSeconds(1));

		boolean accountNonLocked = CommonConstants.STATUS_NORMAL.equals(user.getLockFlag());
		if (!accountNonLocked && user.getLockUntil() != null && user.getLockUntil().isBefore(Instant.now())) {
			accountNonLocked = true;
		}

		assertTrue(accountNonLocked, "lockUntil已过期应视为未锁定");
	}

	@Test
	@DisplayName("UM-06-05: lockUntil未超时应仍为锁定")
	void testLockUntilNotExpiredStillLocked() {
		SysUser user = new SysUser();
		user.setLockFlag(LockFlagEnum.LOCKED);
		user.setLockUntil(Instant.now().plusSeconds(600));

		boolean accountNonLocked = CommonConstants.STATUS_NORMAL.equals(user.getLockFlag());
		if (!accountNonLocked && user.getLockUntil() != null && user.getLockUntil().isBefore(Instant.now())) {
			accountNonLocked = true;
		}

		assertFalse(accountNonLocked, "lockUntil未过期应仍为锁定");
	}

}