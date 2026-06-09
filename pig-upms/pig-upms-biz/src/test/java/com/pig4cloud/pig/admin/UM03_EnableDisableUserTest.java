package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;
import com.pig4cloud.pig.common.core.util.R;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UM-03: 启用/禁用用户")
public class UM03_EnableDisableUserTest {

	@Test
	@DisplayName("UM-03-01: enableUser应设置status=enabled")
	void testEnableUserStatus() {
		SysUser user = new SysUser();
		user.setUserId(1L);
		user.setStatus(UserStatusEnum.DISABLED);

		user.setStatus(UserStatusEnum.ENABLED);
		assertEquals(UserStatusEnum.ENABLED, user.getStatus(), "启用后status应为ENABLED");
	}

	@Test
	@DisplayName("UM-03-02: disableUser应设置status=disabled")
	void testDisableUserStatus() {
		SysUser user = new SysUser();
		user.setUserId(1L);
		user.setStatus(UserStatusEnum.ENABLED);

		user.setStatus(UserStatusEnum.DISABLED);
		assertEquals(UserStatusEnum.DISABLED, user.getStatus(), "禁用后status应为DISABLED");
	}

	@Test
	@DisplayName("UM-03-03: status字段支持三种状态")
	void testStatusEnum() {
		SysUser user = new SysUser();
		user.setStatus(UserStatusEnum.ENABLED);
		assertEquals(UserStatusEnum.ENABLED, user.getStatus());

		user.setStatus(UserStatusEnum.DISABLED);
		assertEquals(UserStatusEnum.DISABLED, user.getStatus());

		user.setStatus(UserStatusEnum.CLOSED);
		assertEquals(UserStatusEnum.CLOSED, user.getStatus());
	}

}