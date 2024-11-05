package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.entity.RegisterDetails;
import com.shopping.model.ForgotPassword;
import com.shopping.model.LoginDetails;
import com.shopping.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/getlogin")
	public String getLoginForm(Model model, @ModelAttribute("message") String message,
			@ModelAttribute("status") String status) {
		if (message != null) {
			model.addAttribute("message", message);
		}
		if (status != null) {
			model.addAttribute("status", status);
		}
		model.addAttribute("user", new LoginDetails());
		model.addAttribute("details", new RegisterDetails());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginDetails details, Model model, RedirectAttributes redirectAttributes) {
		boolean status = loginService.loginStatus(details.getEmail(), details.getPassword());
		if (status) {
			model.addAttribute("isLoggedIn", status);
			model.addAttribute("details", loginService.findByUserName(details.getEmail(), details.getPassword()));
			return "profile";
		} else {
			redirectAttributes.addFlashAttribute("message", "Login Failed, Check the credentials");
			return "redirect:/getlogin";
		}
	}

	@PostMapping("/check-email")
	public ResponseEntity<Boolean> register(@RequestBody String email) {

		// Remove surrounding quotes from email string
		email = email.replaceAll("^\"|\"$", "");
		boolean emailExists = loginService.checkEmailExists(email);
		return ResponseEntity.ok(emailExists);
	}

	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute RegisterDetails register, RedirectAttributes redirectAttributes) {
		String status = loginService.saveUser(register);
		redirectAttributes.addFlashAttribute("status", status);
		return "redirect:/getlogin";
	}

	@GetMapping("/forgot-password")
	public String getForgotPasswordForm(@ModelAttribute("password") String password, Model model) {
		model.addAttribute("password", password);
		model.addAttribute("forgotpassword", new ForgotPassword());
		return "forgot-password";
	}

	@PostMapping("/forgot-password")
	public String getPassword(@ModelAttribute ForgotPassword forgotPassword, RedirectAttributes redirectAttributes) {
		String password = loginService.getForgotPassword(forgotPassword);
		if (password != null) {
			redirectAttributes.addFlashAttribute("password", password);
		} else {
			redirectAttributes.addFlashAttribute("password", "User Not Found");
		}
		return "redirect:/forgot-password";
	}
}
