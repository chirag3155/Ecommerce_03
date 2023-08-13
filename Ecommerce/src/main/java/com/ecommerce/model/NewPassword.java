package com.ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewPassword {

	@NotBlank(message = "Password can not be empty !!")
	@Size(min = 3, max = 12, message = "Password must be between 3-12 characters !")
//	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",message="Password must be Strong")
	private String password;

	@NotBlank(message = "Password can not be empty !!")
	@Size(min = 3, max = 12, message = "Password must be between 3-12 characters !")
//	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",message="Password must be Strong")
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

}
