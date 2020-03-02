package com.azure.partnercenter.customer.dto;

import lombok.Data;

@Data
public class CustomerDto {
	private String customerId;
	private String commerceId;
	private String relationshipToPartner;
	private Boolean allowDelegatedAccess;
	private String customDomains;
	private String associatedPartnerId;
    private String companyName;
}
