package com.udemy.springboot.myfirstwebapp.Example.LoginExample;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("Ayush262");
		boolean isValidPassword = password.equalsIgnoreCase("Ayush@123");
		
		return isValidUserName && isValidPassword;
	}

}
