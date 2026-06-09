package com.pig4cloud.pig.admin.api.dto;

import lombok.Data;

@Data
public class RevokeClientDTO {
	private Long userId;
	private Long clientId;
}