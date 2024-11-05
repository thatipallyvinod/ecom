package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.RegisterDetails;
import com.shopping.model.ForgotPassword;
import com.shopping.repository.LoginRepository;
import com.shopping.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public RegisterDetails findByUserName(String username, String password) {
		return loginRepository.findUser(username, password);
	}

	public boolean loginStatus(String username, String password) {
		RegisterDetails registerDetails = findByUserName(username, password);

		if (registerDetails == null) {
			return false;
		}
		return true;
	}

	public String saveUser(RegisterDetails register) {
		RegisterDetails details = loginRepository.save(register);
		if (details != null) {
			return "User Registered Successfully. You can login now...";
		} else {
			return "User registration Failed...";
		}
	}
	
	public boolean checkEmailExists(String email) {
		boolean details = loginRepository.existsByEmail(email);		
		return details;
	}
	
	public String getForgotPassword(ForgotPassword forgotPassword) {
		RegisterDetails registerDetails = loginRepository.findPassword(forgotPassword.getEmail(), forgotPassword.getMobile());
		if(registerDetails != null) {
			return registerDetails.getPassword();
		}
		return null;
	}

}
