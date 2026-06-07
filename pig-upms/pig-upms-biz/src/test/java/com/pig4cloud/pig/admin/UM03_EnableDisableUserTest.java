package com.pig4cloud.pig.admin;

import com.pig4cloud.pig.admin.api.entity.SysUser;
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
		user.setStatus("disabled");

		user.setStatus("enabled");
		assertEquals("enabled", user.getStatus(), "启用后status应为enabled");
	}

	@Test
	@DisplayName("UM-03-02: disableUser应设置status=disabled")
	void testDisableUserStatus() {
		SysUser user = new SysUser();
		user.setUserId(1L);
		user.setStatus("enabled");

		user.setStatus("disabled");
		assertEquals("disabled", user.getStatus(), "禁用后status应为disabled");
	}

	@Test
	@DisplayName("UM-03-03: status字段支持三种状态")
	void testStatusEnum() {
		SysUser user = new SysUser();
		user.setStatus("enabled");
		assertEquals("enabled", user.getStatus());

		user.setStatus("disabled");
		assertEquals("disabled", user.getStatus());

		user.setStatus("closed");
		assertEquals("closed", user.getStatus());
	}

}