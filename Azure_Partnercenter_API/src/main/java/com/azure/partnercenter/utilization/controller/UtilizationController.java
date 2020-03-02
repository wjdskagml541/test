package com.azure.partnercenter.utilization.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.auth.AuthUserLogin;
import com.azure.partnercenter.utilization.dto.UtilizationDto;
import com.azure.partnercenter.utilization.service.UtilizationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.microsoft.store.partnercenter.IAggregatePartner;
import com.microsoft.store.partnercenter.enumerators.IResourceCollectionEnumerator;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.ratecards.AzureRateCard;
import com.microsoft.store.partnercenter.models.utilizations.AzureInstanceData;
import com.microsoft.store.partnercenter.models.utilizations.AzureResource;
import com.microsoft.store.partnercenter.models.utilizations.AzureUtilizationGranularity;
import com.microsoft.store.partnercenter.models.utilizations.AzureUtilizationRecord;

@Controller
public class UtilizationController {
	
	@Autowired
	AuthUserLogin userLogin;
	
	@Autowired
	UtilizationService utilizationService;
	
	@RequestMapping("/utilizationRecords")
	public String getUtilizationRecords(){
		
		String customerId = "d90bf8fd-96c2-4236-be5a-ec55338846f0";
		String subscriptionId = "BD09A2F6-EEE7-4CBB-A1C9-C69730499B83";
		
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
	
		ResourceCollection<AzureUtilizationRecord> utilizationRecords = partnerOperations.getCustomers()
				  .byId(customerId).getSubscriptions().byId(subscriptionId)
				  .getUtilization().getAzure().query(
				      new DateTime().minusMonths(1),
				      new DateTime(),
				      AzureUtilizationGranularity.DAILY,
				      false,
				      100);
        
		ObjectMapper mapper =
	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
		
		try {
			System.out.println( mapper.writeValueAsString( utilizationRecords ) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
     

		return "test";
	}
	
	@RequestMapping("/insertUtilization")
	public String insertUtilization(){
		
		String customerId = "7bb27480-cf5b-4379-9594-4c05299b520b";
		String subscriptionId = "393ED278-A700-4DFC-B824-E33A10C0B88E";
		
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
	
		ResourceCollection<AzureUtilizationRecord> utilizationRecords = partnerOperations.getCustomers()
				  .byId(customerId).getSubscriptions().byId(subscriptionId)
				  .getUtilization().getAzure().query(
				      new DateTime().minusYears(1),
				      new DateTime(),
				      AzureUtilizationGranularity.DAILY,
				      true,
				      1000);

		// Create an Azure utilization enumerator which will aid us in traversing the utilization pages
        IResourceCollectionEnumerator<ResourceCollection<AzureUtilizationRecord>> utilizationRecordEnumerator = 
        		partnerOperations.getEnumerators().getUtilization().getAzure().create(utilizationRecords);

        while (utilizationRecordEnumerator.hasValue())
        {
        	UtilizationDto utilizationDto = new UtilizationDto(); 
        	
        	ArrayList<AzureUtilizationRecord> list = (ArrayList<AzureUtilizationRecord>) utilizationRecordEnumerator.getCurrent().getItems();
            for(AzureUtilizationRecord azureUtilizationRecord : list){
            	
            	AzureResource resource = azureUtilizationRecord.getResource();
            	AzureInstanceData instanceData = azureUtilizationRecord.getInstanceData();
            	
            	//instanceData.getTags().entrySet();
            	
            	utilizationDto.setCustomerId(customerId);
            	utilizationDto.setSubscriptionId(subscriptionId);
            	utilizationDto.setUsageStartTime(DateTimeFormat.forPattern("yyyyMMddHHmmss").print(azureUtilizationRecord.getUsageStartTime()));
            	utilizationDto.setUsageEndTime(DateTimeFormat.forPattern("yyyyMMddHHmmss").print(azureUtilizationRecord.getUsageEndTime()));
            	utilizationDto.setQuantity(String.format("%f", azureUtilizationRecord.getQuantity()));
            	utilizationDto.setUnit(azureUtilizationRecord.getUnit());
            //	utilizationDto.setInfoFields();
            	utilizationDto.setResourceId(resource.getId());
            	utilizationDto.setResourceName(resource.getName());
                utilizationDto.setCategory(resource.getCategory());
                utilizationDto.setSubcategory(resource.getSubcategory());
                utilizationDto.setRegion(resource.getRegion());
                utilizationDto.setResourceUri(instanceData.getResourceUri());
            //  utilizationDto.setTags(instanceData.getTags());
                
                utilizationService.insertUtilization(utilizationDto);
            
            }
            
            ObjectMapper mapper =
		            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
			try {
				
				System.out.println( mapper.writeValueAsString( utilizationRecordEnumerator.getCurrent() ) );
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
            
            utilizationRecordEnumerator.next();
          
        }
        
		return "test";
	}
	
	
	@RequestMapping("/rateCard")
	public String getRateCard(){
		
		
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();

		AzureRateCard azureRateCard = partnerOperations.getRateCards().getAzure().get( "KRW" , "KR" );
        
		ObjectMapper mapper =
	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
		
		System.out.println(azureRateCard.toString());
		
		try {
			System.out.println( mapper.writeValueAsString( azureRateCard ) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "test";
	}
	
	@RequestMapping("/sharedRateCard")
	public String getSharedRateCard(){
		
		
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();

		AzureRateCard azureSharedRateCard = partnerOperations.getRateCards().getAzure().getShared( "" , "" );
        
		ObjectMapper mapper =
	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
		
		System.out.println(azureSharedRateCard.toString());
		
		try {
			System.out.println( mapper.writeValueAsString( azureSharedRateCard ) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
     

		return "test";
	}
}
