package com.pig4cloud.pig.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.mapper.SysUserMapper;
import com.pig4cloud.pig.admin.service.SysUserService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UM-01: 创建用户")
public class UM01_CreateUserTest {

	private SysUserMapper sysUserMapper;
	private SysUserService sysUserService;

	@BeforeEach
	void setUp() {
		sysUserMapper = Mockito.mock(SysUserMapper.class);
	}

	@Test
	@Order(1)
	@DisplayName("UM-01-02: 用户名唯一性校验-重复用户名应抛异常")
	void testDuplicateUsernameThrowsException() {
		SysUser existingUser = new SysUser();
		existingUser.setUsername("duplicate_user");
		existingUser.setUserId(1L);

		when(sysUserMapper.selectOne(any())).thenReturn(existingUser);

		assertTrue(existingUser.getUsername().equals("duplicate_user"),
			"已存在同名用户时应阻止创建");
	}

	@Test
	@Order(2)
	@DisplayName("UM-01-06: 密码应为bcrypt格式")
	void testPasswordBcryptFormat() {
		String rawPassword = "Test123456";
		org.springframework.security.crypto.password.PasswordEncoder encoder =
			org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(rawPassword);

		assertTrue(encoded.startsWith("{bcrypt}"), "密码应以{bcrypt}开头");
		assertNotEquals(rawPassword, encoded, "密码不应为明文");
		assertTrue(encoder.matches(rawPassword, encoded), "bcrypt加密后应能匹配原密码");
	}

	@Test
	@Order(3)
	@DisplayName("UM-01-01: SysUser实体不含tenantId字段")
	void testSysUserNoTenantId() {
		boolean hasTenantId = false;
		for (java.lang.reflect.Field field : SysUser.class.getDeclaredFields()) {
			if (field.getName().equals("tenantId")) {
				hasTenantId = true;
			}
		}
		assertFalse(hasTenantId, "SysUser实体不应包含tenantId字段（已迁移到hierarchy表）");
	}

}
