package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchCheckPermissionDTO {
	private Long userId;
	private List<String> permCodes;
}