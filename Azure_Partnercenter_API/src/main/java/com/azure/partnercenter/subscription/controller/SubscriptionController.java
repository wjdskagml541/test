package com.azure.partnercenter.subscription.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.auth.AuthUserLogin;
import com.azure.partnercenter.customer.service.CustomerService;
import com.azure.partnercenter.subscription.dto.SubscriptionDto;
import com.azure.partnercenter.subscription.service.SubscriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.microsoft.store.partnercenter.IAggregatePartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.subscriptions.Subscription;

@Controller
public class SubscriptionController {
	
	@Autowired
	AuthUserLogin userLogin;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@RequestMapping("/subscriptions")
	public String getSubscriptions(){
		
		String customerId = "d90bf8fd-96c2-4236-be5a-ec55338846f0";
	
		//인증
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
		
        ResourceCollection<Subscription> customerSubscriptions = partnerOperations.getCustomers().byId( customerId ).getSubscriptions().get();
        
		ObjectMapper mapper =
	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
		
		
		try {
			System.out.println( mapper.writeValueAsString( customerSubscriptions ) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
     

		return "test";
	}
	
	@RequestMapping("/insertSubscriptions")
	public String insertSubscriptions(){
		
//		String customerId = "d90bf8fd-96c2-4236-be5a-ec55338846f0";
		
		List<String> customerIdList = customerService.selectCustomerList();
	
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
		
		for(String customerId : customerIdList){
			
			SubscriptionDto subscriptionDto = new SubscriptionDto();
			subscriptionDto.setCustomerId(customerId);
			
			ResourceCollection<Subscription> customerSubscriptions = partnerOperations.getCustomers().byId( customerId ).getSubscriptions().get();
	        ArrayList<Subscription> list = (ArrayList<Subscription>) customerSubscriptions.getItems();
	        
	        for(Subscription subscription : list) {
	        	subscriptionDto.setSubscriptionId(subscription.getId());
	        	subscriptionDto.setSubscriptionName(subscription.getFriendlyName());
	        	subscriptionDto.setStatus(subscription.getStatus().toString());
	        	subscriptionDto.setBillingType(subscription.getBillingType().toString());
	        	subscriptionDto.setBillingCycle(subscription.getBillingCycle().toString());
	        	subscriptionDto.setIsTrial(subscription.getIsTrial());
	        
	        	subscriptionService.insertSubscription(subscriptionDto);
	        }
		    
		}

		return "test";
	}

}
