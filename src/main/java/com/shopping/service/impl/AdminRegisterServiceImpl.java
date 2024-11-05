package com.shopping.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shopping.entity.Admin;
import com.shopping.repository.AdminRegisterRepository;
import com.shopping.service.AdminRegisterService;

@Service
public class AdminRegisterServiceImpl implements AdminRegisterService {

	@Value("${spring.mail.username}")
	private String senderEmail;
	
	@Autowired
	private AdminRegisterRepository adminRegisterRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public long registerAdmin(Admin admin) {
		Random random = new Random();
		long randomNumber = random.nextInt(900000) + 100000;
		String authId = String.valueOf(randomNumber);
		admin.setAuthId(authId);
		Admin savedAdmin = adminRegisterRepository.save(admin);
		sendAuthId(admin.getEmail(), authId);
		if (savedAdmin != null)
			return randomNumber;
		return 0;
	}

	public boolean isValidAdmin(String email, String authId) {
		boolean status = false;
		Admin admin = adminRegisterRepository.findUser(email, authId);
		if (admin != null)
			status = true;
		return status;
	}
	
	public void sendAuthId(String email, String authId) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(senderEmail);
			message.setTo(email);
			message.setSubject("Admin Auth Id");
			message.setText("Your Authorization Id for Register as Admin is: "+authId);

			javaMailSender.send(message);
			System.out.println("Email send successfully...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Email send Failed...");
		}
	}
}
