package com.azure.partnercenter.auth.dto;


import lombok.Data;

@Data
public class AuthToken {

     String accessToken;
     String refreshToken;
     String domain;
}
