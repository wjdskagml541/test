package com.azure.partnercenter.utilization.dto;

import lombok.Data;

@Data
public class UtilizationDto {
	private String customerId;
	private String subscriptionId;
	private String usageStartTime;
    private String usageEndTime;
    private String quantity;
    private String unit;
    private String infoFields;
    private String resourceId;
    private String resourceName;
    private String category;
    private String subcategory;
    private String region;
    private String resourceUri;
    private String tags;
}
