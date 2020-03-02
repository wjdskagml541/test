package com.azure.partnercenter.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.partnercenter.common.AzureConfig;
import com.microsoft.store.partnercenter.IAadLoginHandler;
import com.microsoft.store.partnercenter.IAggregatePartner;
import com.microsoft.store.partnercenter.IPartnerCredentials;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.extensions.PartnerCredentials;

@Service
public class AuthUserLogin {
	
	@Autowired
	AzureConfig azureConfiguration;
	
	@Autowired
	AadUserLoginHandler aadUserLoginHandler;
	
	private IAggregatePartner partnerOperations = null;

	
	public IAggregatePartner getUserPartnerOperations()
	{
		
		if(this.partnerOperations == null) {
			
		    IAadLoginHandler loginHandler = aadUserLoginHandler;
	
		    IPartnerCredentials userCredentials = PartnerCredentials.getInstance().generateByUserCredentials(
		    		azureConfiguration.getApplicationId(),
		            loginHandler.authenticate(),
		            loginHandler );
		    
		    this.partnerOperations = PartnerService.getInstance().createPartnerOperations(userCredentials);
	    
		}
	    
	    return this.partnerOperations;
	}
}
