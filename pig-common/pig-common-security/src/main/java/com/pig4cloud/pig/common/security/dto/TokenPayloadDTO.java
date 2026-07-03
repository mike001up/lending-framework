package com.pig4cloud.pig.common.security.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) 
public class TokenPayloadDTO {
// ---------- 标准 JWT 字段（两者共有） ----------
    // 主题（客户端ID 或 用户名）
    private String sub;  
    // 受众（通常为客户端ID）    
    private String aud;   
    // 签发者   
    private String iss;   
    // 过期时间（时间戳）   
    private Instant exp; 
    // 生效时间（时间戳）       
    private Instant nbf;  
    // 签发时间（时间戳）      
    private Instant iat;     
    // JWT唯一标识   
    private String jti;      

    // ---------- OAuth2 / PigX 自定义字段（两者共有） ----------
    // 客户端标识
    private String clientId; 
    // 权限范围
    private String scope;   
    // 许可证信息（PigX自定义） 
    private String license;  

    // ---------- 用户Token独有字段（客户端Token中缺失，可空） ----------
     // 用户信息标识（如 "pigUser"）
    private String userInfo;
    // 用户ID（如 "1"）
    private Long userId;
    private String username;
    private Long tenantId;
    //用于标识 token 是用户还是客户端
    private GrantTypeEnum grantType;
}
