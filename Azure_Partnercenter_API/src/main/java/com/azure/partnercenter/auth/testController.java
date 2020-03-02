package com.azure.partnercenter.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azure.partnercenter.auth.service.AuthService;


@Controller
public class testController {
	
	@Autowired
	AuthService authService;

	
	@RequestMapping(value = "/helloworld")
	public String helloworld(ModelMap model) throws Exception {
		

		return "test";
	}
	

}
