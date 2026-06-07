package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

@Data
public class RevokeScopeDTO {
	private Long userId;
	private Long scopeId;
}