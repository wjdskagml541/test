
package com.azure.partnercenter.auth;

public enum ResponseCode {

  SUCCESS(200, "OK"),
  AUTHORIZE_FAIL(400, "Authorize Fail"), //인증 실패
  BAD_REQUEST(400,"BAD_REQUEST"), //잘못된 요청
  UNAUTHORIZED(401, "Unauthorized"), //권한없음
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"), //메소드 없음.
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"); //내부서버오류

  private final int code;
  private final String message;

  ResponseCode(final int code, final String message){
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public int getCode() {
    return this.code;
  }
}
