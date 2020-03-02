package com.azure.partnercenter.auth;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthorizationApi {
		
	/**
	* 관리자 계정 인증 토큰 발급
	* 
	* @param
	* @return
	* @throws 
	*/
	@POST("/{tenantId}/oauth2/token")
	Call<Authorization> certification(@Path("tenantId") String tenant, @Body RequestBody body);

}
