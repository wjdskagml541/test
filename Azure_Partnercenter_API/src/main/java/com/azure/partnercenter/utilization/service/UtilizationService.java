package com.azure.partnercenter.utilization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.partnercenter.utilization.dto.UtilizationDto;
import com.azure.partnercenter.utilization.mapper.UtilizationMapper;

@Service
public class UtilizationService {

	@Autowired
	UtilizationMapper utilizationMapper;
	
	public void insertUtilization(UtilizationDto utilizationDto) {
		utilizationMapper.insertUtilization(utilizationDto);
	}
	

}
