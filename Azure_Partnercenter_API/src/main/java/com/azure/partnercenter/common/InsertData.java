package com.azure.partnercenter.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.auth.AuthUserLogin;

@Controller
public class InsertData {
	
	@Autowired
	AuthUserLogin userLogin;

	@RequestMapping("/insertData")
	public String insertData(){
	

		return "insertDataView";
	}
	
	
}
