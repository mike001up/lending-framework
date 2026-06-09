package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchGrantClientDTO {
	private Long userId;
	private List<Long> clientIds;
}