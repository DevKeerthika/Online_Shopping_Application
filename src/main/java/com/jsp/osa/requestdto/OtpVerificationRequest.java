package com.jsp.osa.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerificationRequest 
{
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be ending with @gmail.com")
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Otp is required")
	@Pattern(regexp = "^[0-9]{6}$", message = "Otp length must be six numerical values")
	private String otp;
}
