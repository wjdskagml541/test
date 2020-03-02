package com.azure.partnercenter.auth;

import lombok.Data;


@Data
public class Authorization {
    private String token_type;
    private String scope;
    private String expires_in;
    private String ext_expires_in;
    private String access_token;
    private String refresh_token;
    private String expires_on;
    private String not_before;
}
