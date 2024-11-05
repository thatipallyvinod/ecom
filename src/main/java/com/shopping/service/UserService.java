package com.shopping.service;

import com.shopping.entity.RegisterDetails;

public interface UserService {
	public RegisterDetails findUserByJwtToken(String jwt) throws Exception;
	
	public RegisterDetails findUserByEmail(String email) throws Exception;
}
