package com.azure.partnercenter.subscription.dto;

import lombok.Data;

@Data
public class SubscriptionDto {
	private String customerId;
	private String subscriptionId;
    private String subscriptionName;
    private String status;
    private String billingType;
    private String billingCycle;
    private Boolean isTrial;
}
