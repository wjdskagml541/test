package com.azure.partnercenter.customer.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.auth.AuthUserLogin;
import com.azure.partnercenter.customer.dto.CustomerDto;
import com.azure.partnercenter.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.microsoft.store.partnercenter.IAggregatePartner;
import com.microsoft.store.partnercenter.enumerators.IResourceCollectionEnumerator;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.SeekBasedResourceCollection;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.models.query.QueryFactory;
import com.microsoft.store.partnercenter.models.relationships.PartnerRelationship;
import com.microsoft.store.partnercenter.models.relationships.PartnerRelationshipType;

@Controller
public class CustomerController {
	
	@Autowired
	AuthUserLogin userLogin;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	tset test;
	
	@RequestMapping(value = "/test")
	public String test(){
		System.out.println(test.test());
		return "test";
	}

	@RequestMapping(value = "/customers")
	public String getCustomers(){
	
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
		
		SeekBasedResourceCollection<Customer> customersPage = partnerOperations.getCustomers().query(QueryFactory.getInstance().buildIndexedQuery(40));
		
		IResourceCollectionEnumerator<SeekBasedResourceCollection<Customer>> customersEnumerator =
			    partnerOperations.getEnumerators().getCustomers().create( customersPage );
		
		 while ( customersEnumerator.hasValue() )
	     {
			   
			   ArrayList<Customer> list = (ArrayList<Customer>) customersEnumerator.getCurrent().getItems();
			   
				ObjectMapper mapper =
			            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).registerModule( new JodaModule() );
				try {
					
					System.out.println( mapper.writeValueAsString( customersEnumerator.getCurrent() ) );
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

	            customersEnumerator.next();
	    }

		return "test";
	}
	
	@RequestMapping(value = "/reseller")
	public String getReseller(){
	
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
		
		//ResourceCollection<PartnerRelationship> indirectProvider = partnerOperations.getRelationships().get(PartnerRelationshipType.IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF);
		
		ResourceCollection<PartnerRelationship> indirectReseller = partnerOperations.getRelationships().get(PartnerRelationshipType.IS_INDIRECT_RESELLER_OF);
		
		ObjectMapper mapper =
	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
		
		
		try {
			//System.out.println("--------------------------------indirectProvider------------------------------------");
			//System.out.println( mapper.writeValueAsString( indirectProvider ) );
			System.out.println("--------------------------------indirectReseller------------------------------------");
			System.out.println( mapper.writeValueAsString( indirectReseller ) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "test";
	}
	
//	@RequestMapping(value = "/resellerteste")
//	public String getResellerteste(){
//	
//		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
//		
//		
//		ResourceCollection<PartnerRelationship> indirectReseller = partnerOperations.getRelationships().query(PartnerRelationshipType.IS_INDIRECT_RESELLER_OF,);
//		
//		ObjectMapper mapper =
//	            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true ).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule( new JodaModule() );
//		
//		
//		try {
//			//System.out.println("--------------------------------indirectProvider------------------------------------");
//			//System.out.println( mapper.writeValueAsString( indirectProvider ) );
//			System.out.println("--------------------------------indirectReseller------------------------------------");
//			System.out.println( mapper.writeValueAsString( indirectReseller ) );
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//
//		return "test";
//	}
	
	
	@RequestMapping(value = "/insertCustomers")
	public String insertCustomers(){
		
		IAggregatePartner partnerOperations = userLogin.getUserPartnerOperations();
		
		SeekBasedResourceCollection<Customer> customersPage = partnerOperations.getCustomers().query(QueryFactory.getInstance().buildIndexedQuery(40));
		
		IResourceCollectionEnumerator<SeekBasedResourceCollection<Customer>> customersEnumerator =
			    partnerOperations.getEnumerators().getCustomers().create( customersPage );
		
		 while ( customersEnumerator.hasValue() )
	     {
			   CustomerDto customerDto = new CustomerDto();
			   ArrayList<Customer> list = (ArrayList<Customer>) customersEnumerator.getCurrent().getItems();
			   
			   for(Customer customer : list){
				   
				   customerDto.setCustomerId(customer.getId());
				   customerDto.setCommerceId(customer.getCommerceId());
				   customerDto.setRelationshipToPartner(customer.getRelationshipToPartner().toString());
				   customerDto.setAllowDelegatedAccess(customer.getAllowDelegatedAccess());
				   customerDto.setAssociatedPartnerId(customer.getAssociatedPartnerId());
				   customerDto.setCompanyName(customer.getCompanyProfile().getCompanyName());
				   
//				   List<String> customDomains = customer.getCustomDomains();
//				   if(customDomains != null){
//					   StringBuilder sb = new StringBuilder();
//					   for(String customDomain : customDomains) {
//						   sb.append(customDomain);
//					   }
//					   customerDto.setCustomDomains(sb.toString());
//				   }
				   
				   customerService.insertCustomer(customerDto);
		        }
			   
	            customersEnumerator.next();
	    }

		return "test";
	}
	
	
}
