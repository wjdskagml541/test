package com.azure.partnercenter.auth;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.RetrofitFactory;
import com.azure.partnercenter.auth.dto.AuthToken;
import com.azure.partnercenter.auth.service.AuthService;

import okhttp3.MultipartBody;
import retrofit2.Response;


@Controller
public class loginController {
	
	@Autowired
	AuthService authService;

	
	@RequestMapping(value = "/login")
	public String loginSckcloud(ModelMap model) throws Exception {

		//String redirectUri = URLEncoder.encode("http://localhost:8081/login/sckcorp/callback","UTF-8");
		
		String redirectUri = URLEncoder.encode("http://localhost:8081/login/callback","UTF-8");
		
		//String URI26 = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=84b96132-780a-4948-b8fb-db6fb660dd87&response_type=code&redirect_uri=http://localhost:8081/login/sckcorp/callback&scope=https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation";
		
		//String URI1 = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=65b68f11-2c9b-4ede-b2c6-baf367e51a3e&response_type=code&redirect_uri=http://localhost:8081/login/sckcloud/callback&scope=https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation";
		
		//sptek테스트
		String URI1 = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=6741dc05-403f-4e86-a70e-abb7497c3ebc&response_type=code&redirect_uri="+redirectUri+"&scope=https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation";
		
		//model.addAttribute("URI26", URI26);
		model.addAttribute("URI1", URI1);
		

		return "logintest";
	}
	
	/*
	 * 26일자 호출 테스트
	 * */
	@RequestMapping(value = "/login/sckcorp/callback")
	public String loginSckcorpCallback(String code, ModelMap model) throws Exception {
		System.out.println("code : " + code );
		
		AuthorizationApi api = RetrofitFactory.createAuthorization("https://login.microsoftonline.com");
		
		final String tenantId ="common";
		final String domain = "sckcorp.co.kr";
        final String clientId ="84b96132-780a-4948-b8fb-db6fb660dd87";
        final String clientSecret ="dnrq2yyvvnUfJ9OB1o935YsINs1Q6nvmelMhfMHOOtQ=";
        final String redirect_uri= "http://localhost:8081/login/sckcorp/callback";
		
		try {

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            builder.addFormDataPart("grant_type", "authorization_code");
            builder.addFormDataPart("code", code);
            builder.addFormDataPart("client_id", clientId);
            builder.addFormDataPart("client_secret", clientSecret);
            builder.addFormDataPart("scope", "https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation");
            builder.addFormDataPart("redirect_uri", redirect_uri);

            MultipartBody request = builder.build();
            Response<Authorization> response = null;
            response = api.certification(tenantId, request).execute();

            if(response.isSuccessful()) {
                final Authorization auth = response.body();

                AuthToken authToken = new AuthToken();
                authToken.setAccessToken(auth.getAccess_token());
                authToken.setRefreshToken(auth.getRefresh_token());
                authToken.setDomain(domain);

                authService.insertAuth(authToken);
                
                model.addAttribute("result", "success");
                
                return "logintoken";
                
            }
            else {
            	model.addAttribute("result", "fail");
            	
            	return "logintoken";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		model.addAttribute("result", "fail");
		return "logintoken";
		
	}
	
	
	/*
	 * 1일자 호출 테스트
	 * */
	@RequestMapping(value = "/login/sckcloud/callback")
	public String loginSckcloudCallback(String code, ModelMap model) throws Exception {
		
		System.out.println("code : " + code );
		
		AuthorizationApi api = RetrofitFactory.createAuthorization("https://login.microsoftonline.com");
		
		final String tenantId ="common";
		final String domain = "sckcloud.com";
        final String clientId ="65b68f11-2c9b-4ede-b2c6-baf367e51a3e";
        final String clientSecret ="SJw3yPYRDGm2N9Hx4phd1/6r30tBeHtijBnsygx4YhA=";
        final String redirect_uri= "http://localhost:8081/login/sckcloud/callback";
		
		try {

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            builder.addFormDataPart("grant_type", "authorization_code");
            builder.addFormDataPart("code", code);
            builder.addFormDataPart("client_id", clientId);
            builder.addFormDataPart("client_secret", clientSecret);
            builder.addFormDataPart("scope", "https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation");
            builder.addFormDataPart("redirect_uri", redirect_uri);

            MultipartBody request = builder.build();
            Response<Authorization> response = null;
            response = api.certification(tenantId, request).execute();

            if(response.isSuccessful()) {
                final Authorization auth = response.body();

                AuthToken authToken = new AuthToken();
                authToken.setAccessToken(auth.getAccess_token());
                authToken.setRefreshToken(auth.getRefresh_token());
                authToken.setDomain(domain);

                authService.insertAuth(authToken);
                
                model.addAttribute("result", "success");
                
                return "logintoken";
                
            }
            else {
            	model.addAttribute("result", "fail");
            	
            	return "logintoken";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		model.addAttribute("result", "fail");
		return "logintoken";
		
	}
	
	/*
	 * sptek호출 테스트
	 * */
	@RequestMapping(value = "/login/callback")
	public String loginCallback(String code, ModelMap model) throws Exception {

		System.out.println("code : " + code );
		
		AuthorizationApi api = RetrofitFactory.createAuthorization("https://login.microsoftonline.com");
		
		final String tenantId ="common";
		final String domain = "test";
        final String clientId ="6741dc05-403f-4e86-a70e-abb7497c3ebc";
        final String clientSecret ="j9MoEqgEwSkSQdAqy9Jbus/JdPzXuWtr+pE1gP7J2oo=";
        final String redirect_uri= "http://localhost:8081/login/callback";
		
		try {

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            builder.addFormDataPart("grant_type", "authorization_code");
            builder.addFormDataPart("code", code);
            builder.addFormDataPart("client_id", clientId);
            builder.addFormDataPart("client_secret", clientSecret);
            builder.addFormDataPart("scope", "https://api.partnercenter.microsoft.com/user_impersonation https://management.azure.com/user_impersonation");
            builder.addFormDataPart("redirect_uri", redirect_uri);

            MultipartBody request = builder.build();
            Response<Authorization> response = null;
            response = api.certification(tenantId, request).execute();

            if(response.isSuccessful()) {
                final Authorization auth = response.body();

                AuthToken authToken = new AuthToken();
                authToken.setAccessToken(auth.getAccess_token());
                authToken.setRefreshToken(auth.getRefresh_token());
                authToken.setDomain(domain);

                authService.insertAuth(authToken);
                
                model.addAttribute("result", "success");
                
                return "logintoken";
                
            }
            else {
            	model.addAttribute("result", "fail");
            	
            	return "logintoken";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		model.addAttribute("result", "fail");
		return "logintoken";
	}

}
