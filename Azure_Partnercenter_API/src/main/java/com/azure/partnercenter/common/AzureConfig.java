package com.azure.partnercenter.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureConfig {

	@Value("${PartnerServiceSettings.PartnerServiceApiEndpoint}")
	private String PartnerServiceApiEndpoint;
	
	@Value("${PartnerServiceSettings.AuthenticationAuthorityEndpoint}")
	private String AuthenticationAuthorityEndpoint;
	
	@Value("${PartnerServiceSettings.GraphEndpoint}")
	private String GraphEndpoint;
	
	@Value("${PartnerServiceSettings.CommonDomain}")
	private String CommonDomain;
	
	@Value("${UserAuthentication.ClientId}")
	private String ClientId;
	
	@Value("${UserAuthentication.Scope}")
	private String Scope;
	
	@Value("${AppAuthentication.ApplicationId}")
	private String ApplicationId;
	
	@Value("${AppAuthentication.ApplicationSecret}")
	private String ApplicationSecret;
	
	@Value("${AppAuthentication.Domain}")
	private String Domain;

	public String getPartnerServiceApiEndpoint() {
		return PartnerServiceApiEndpoint;
	}

	public String getAuthenticationAuthorityEndpoint() {
		return AuthenticationAuthorityEndpoint;
	}

	public String getGraphEndpoint() {
		return GraphEndpoint;
	}

	public String getCommonDomain() {
		return CommonDomain;
	}

	public String getClientId() {
		return ClientId;
	}

	public String getScope() {
		return Scope;
	}

	public String getApplicationId() {
		return ApplicationId;
	}

	public String getApplicationSecret() {
		return ApplicationSecret;
	}

	public String getDomain() {
		return Domain;
	}

}
