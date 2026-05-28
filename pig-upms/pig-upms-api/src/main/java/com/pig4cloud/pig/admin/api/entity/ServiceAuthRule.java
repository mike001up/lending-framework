package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("service_auth_rule")
public class ServiceAuthRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	private String providerService;

	private String callerService;

	private String pathPattern;

	private String httpMethod;

	private Boolean isAllowed;

	private String description;

	private Long createdBy;

	private LocalDateTime createdAt;

	private Long updatedBy;

	private LocalDateTime updatedAt;

	@TableLogic
	private String isDel;

}
