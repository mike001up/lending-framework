package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchGrantScopeDTO {
	private Long userId;
	private List<Long> scopeIds;
}