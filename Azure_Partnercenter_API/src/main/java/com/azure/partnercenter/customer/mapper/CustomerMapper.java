package com.azure.partnercenter.customer.mapper;

import java.util.List;

import com.azure.partnercenter.customer.dto.CustomerDto;

public interface CustomerMapper {
	public void insertCustomer (CustomerDto customerDto);

	public List<String> selectCustomerList();
}
