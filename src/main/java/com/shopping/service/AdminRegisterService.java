package com.shopping.service;

import com.shopping.entity.Admin;

public interface AdminRegisterService {

	public long registerAdmin(Admin admin);
	
	public boolean isValidAdmin(String email, String authId);
}
