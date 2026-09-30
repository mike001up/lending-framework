package com.pig4cloud.pig.admin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPermissionDTO {
	@NotNull(message = "请提供用户信息")
	private Long userId;
	
	@NotBlank(message = "请提供需要校验的权限")
	private String permCode;
}