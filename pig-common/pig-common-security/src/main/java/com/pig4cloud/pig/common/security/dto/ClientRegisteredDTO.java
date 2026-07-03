package com.pig4cloud.pig.common.security.dto;

import lombok.Data;
import java.util.List;

@Data
public class ClientRegisteredDTO {
    private String clientId;
    private String clientSecret;
    private String authorizedGrantTypes;
    private String redirectUris;
    private String scopes;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private Boolean autoapprove;
}
