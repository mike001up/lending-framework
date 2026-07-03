package com.pig4cloud.pig.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
// import com.pig4cloud.pig.admin.api.dto.UserInfo;
// import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.LockFlagEnum;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.util.RetOps;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;

import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author lengleng
 * @date 2021/12/21
 */
public interface PigUserDetailsService extends UserDetailsService, Ordered {

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	default boolean support(String clientId, String grantType) {
		return true;
	}

	/**
	 * 排序值 默认取最大的
	 * @return 排序值
	 */
	default int getOrder() {
		return 0;
	}

	/**
	 * 构建userdetails
	 * @param result 用户信息
	 * @return UserDetails
	 */
	default UserDetails getUserDetails(PigUserDTO info) {
		// UserInfo info = RetOps.of(result).getData().orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

		Set<String> dbAuthsSet = new HashSet<>();

		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Stream.ofNullable(info.getRoles())
			.flatMap(List::stream)
			.map(role -> SecurityConstants.ROLE + role)
			.forEach(dbAuthsSet::add);
			// 获取资源
			dbAuthsSet.addAll(info.getPermissions());

		}

		Collection<GrantedAuthority> authorities = AuthorityUtils
			.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		// SysUser user = info.getSysUser();

		boolean accountNonLocked = info.getLockFlag() == LockFlagEnum.NORMAL;
		if (!accountNonLocked && info.getLockUntil() != null && info.getLockUntil().isBefore(java.time.Instant.now())) {
			accountNonLocked = true;
		}

		boolean enabled = UserStatusEnum.ENABLED.equals(info.getStatus());

		return new PigUser(info.getId(), info.getUsername(),
				SecurityConstants.BCRYPT + info.getPassword(), info.getPhone(), info.getTenantId(), enabled, true, true,
				accountNonLocked, authorities);
	}

	/**
	 * 通过用户实体查询
	 * @param pigUser user
	 * @return
	 */
	default UserDetails loadUserByUser(PigUser pigUser) {
		return this.loadUserByUsername(pigUser.getUsername());
	}

}
