package com.azure.partnercenter.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.partnercenter.auth.dto.AuthToken;
import com.azure.partnercenter.auth.mapper.AuthMapper;
import com.azure.partnercenter.customer.dto.CustomerDto;
import com.azure.partnercenter.customer.mapper.CustomerMapper;

@Service
public class AuthService {

	@Autowired
	AuthMapper authMapper;
	
	public void insertAuth(AuthToken authToken) {
		authMapper.insertAuth(authToken);
	}
	

}
