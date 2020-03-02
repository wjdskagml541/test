package com.azure.partnercenter.auth.mapper;

import java.util.List;

import com.azure.partnercenter.auth.dto.AuthToken;
import com.azure.partnercenter.customer.dto.CustomerDto;

public interface AuthMapper {

	public void insertAuth(AuthToken authToken);
}
