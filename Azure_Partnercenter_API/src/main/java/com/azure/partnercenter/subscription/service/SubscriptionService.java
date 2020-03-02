package com.azure.partnercenter.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.partnercenter.subscription.dto.SubscriptionDto;
import com.azure.partnercenter.subscription.mapper.SubscriptionMapper;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionMapper subscriptionMapper;
	
	public void insertSubscription(SubscriptionDto subscriptionDto) {
		subscriptionMapper.insertSubscription(subscriptionDto);
	}
	

}
