package com.shopping.service;

import com.shopping.entity.RegisterDetails;
import com.shopping.model.ForgotPassword;

public interface LoginService {

	public RegisterDetails findByUserName(String username, String password);
	
	public boolean loginStatus(String username, String password);
	
	public String saveUser(RegisterDetails register);
	
	public boolean checkEmailExists(String email);
	
	public String getForgotPassword(ForgotPassword forgotPassword);
}
