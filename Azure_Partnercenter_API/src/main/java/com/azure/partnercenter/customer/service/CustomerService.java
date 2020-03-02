package com.azure.partnercenter.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.partnercenter.customer.dto.CustomerDto;
import com.azure.partnercenter.customer.mapper.CustomerMapper;

@Service
public class CustomerService {

	@Autowired
	CustomerMapper customerMapper;
	
	public void insertCustomer(CustomerDto customerDto) {
		customerMapper.insertCustomer(customerDto);
	}
	
	public List<String> selectCustomerList() {
		return customerMapper.selectCustomerList();
	}
	
	public String test() {
		return "777";
	}
}
