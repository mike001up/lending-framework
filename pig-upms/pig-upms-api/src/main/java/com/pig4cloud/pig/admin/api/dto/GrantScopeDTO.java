package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class GrantScopeDTO {
	private Long userId;
	private Long scopeId;
}