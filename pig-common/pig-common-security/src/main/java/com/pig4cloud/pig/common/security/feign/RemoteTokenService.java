package com.pig4cloud.pig.common.security.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.OAuth2AccessTokenDTO;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteTokenService {
	/**
	 * 分页查询token 信息
	 * @param params 分页参数
	 * @return page
	 */
	// @NoToken
	@PostMapping("/token/page")
	R<Page> getTokenPage(@RequestBody Map<String, Object> params);

	/**
	 * 删除token
	 * @param token token
	 * @return
	 */
	// @NoToken
	@DeleteMapping("/token/remove/{token}")
	R<Boolean> removeTokenById(@PathVariable("token") String token);

	// @NoToken
	@DeleteMapping("/token/removeByUsername/{username}")
	@InternalFeign
	R<Boolean> removeTokenByUsername(@PathVariable("username") String username);

	/**
	 * 校验令牌获取用户信息
	 * @param token
	 * @return
	 */
	// @NoToken
	@GetMapping("/token/query-token")
	R<Map<String, Object>> queryToken(@RequestParam("token") String token);
	
	@GetMapping("/token/parsing")
	R<TokenPayloadDTO> parsingToken(@RequestParam("token") String token);

	@PostMapping(value = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	OAuth2AccessTokenDTO clientToken(@RequestBody MultiValueMap<String, String> formData,
            @RequestHeader("Authorization") String authorization);

}
