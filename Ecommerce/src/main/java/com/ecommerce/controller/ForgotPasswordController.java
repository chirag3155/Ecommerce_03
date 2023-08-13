package com.ecommerce.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Otp;
import com.ecommerce.model.VerifyEmail;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.EmailService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ForgotPasswordController {

	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdminService adminService;

	public ForgotPasswordController(CategoryService categoryService, AdminService adminService) {
		super();
		this.categoryService = categoryService;
		this.adminService = adminService;
	}

	@GetMapping("/forgotpassword")
	public String setEmail(Model model) {
		model.addAttribute("verifyEmail", new VerifyEmail());
		return "forgot_password";
	}

	@PostMapping("/forgotpassword")
	public String verifyEmailAndSendOtp(@Valid @ModelAttribute("verifyEmail") VerifyEmail email, BindingResult result,
			Model model, HttpSession session) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "forgot_password"; // "redirect:/students/new"
		}
		String email1 = email.getEmail();
		Admin admin2 = adminService.findByEmail(email.getEmail());
		if (admin2 == null) {
			return "redirect:/forgotpassword";
		}
		String email2 = admin2.getEmail();

		if (email1.equalsIgnoreCase(email2)) {
			session.setAttribute("myemail", email2);
			EmailService emailService=new EmailService();
			Random random = new Random(10000);
			int otp = random.nextInt(999999);
			System.out.println("OTP " + otp);
			String from = "chirag.bhargava09@gmail.com";
			String subject = "OTP from Tekseer";
			String text = "<h1> OTP = " + otp + " </h1>";
			String to = email.getEmail(); //"Bhargava.Chirag@ehrlogic.com" //  email.getEmail()
			boolean f = emailService.sendEmail(to, from, subject, text);
			if (f) {
			    // If the email is sent successfully, set the OTP in the session attribute
			    session.setAttribute("myotp", otp);
			    model.addAttribute("otp", new Otp());
			    return "verifyOtp"; // Redirect to the "verifyOtp" page to verify the OTP
			} else { 
			    // If the email fails to send, set an error message in the session attribute
			    session.setAttribute("message", "Email could not be sent. Please try again later.");
			    return "redirect:/forgotpassword"; // Redirect back to the "forgotpassword" page
			}
		}
		System.out.println("You have entered wrnong registered email");
		return "redirect:/forgotpassword";

	}

	@PostMapping("/verifyotp")
	public String verifyOtp(@Valid @ModelAttribute("otp") Otp otp2,HttpSession session) {
		Integer myotp=(int)session.getAttribute("myotp");
		int otp=otp2.getOtp();
		if(myotp==otp) {
			return "changePassword";
		}
		return "verifyOtp";
	}
	@PostMapping("/changepassword")
	public String newPassword(@RequestParam("newPassword")String newPassword,@RequestParam("confirmPassword")String confirmPassword,
			HttpSession session) {
		if(newPassword.equalsIgnoreCase(confirmPassword)) {
			String email=(String)session.getAttribute("myemail");
			Admin admin=adminService.findByEmail(email);
			admin.setEmail(email);
			admin.setPassword(newPassword);
			adminService.saveAdmin(admin);
			return "redirect:/admin/new";
		}
		return "changePassword";
	}



	
}
