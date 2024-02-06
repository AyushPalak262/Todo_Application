package com.udemy.springboot.myfirstwebapp.Example.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController1 {
	
	private Logger logger= LoggerFactory.getLogger(getClass());
	
	@RequestMapping("login1")
	public String gotoLoginPage(@RequestParam String name,ModelMap map) {
		System.out.println("The requestParam coming from browser ="+ name);
		map.put("name",name);
		logger.debug("RequestParam is {}",name);
		logger.info("RequestParam is {}",name);
		logger.warn("RequestParam is {}",name);
		return "Example/login1";
	}

}
