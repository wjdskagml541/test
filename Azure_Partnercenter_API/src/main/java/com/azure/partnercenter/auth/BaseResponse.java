package com.azure.partnercenter.auth;

import lombok.Data;

@Data
public class BaseResponse {

    private int code;
    private String message;

    BaseResponse(){ }

    BaseResponse(ResponseCode code){
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static BaseResponse of(ResponseCode code){
        return new BaseResponse(code);
    }
}
