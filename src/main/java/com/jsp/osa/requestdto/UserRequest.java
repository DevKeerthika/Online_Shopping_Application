package com.jsp.osa.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest 
{
	@NotNull(message = "username cannot be null")
	@NotBlank(message = "username cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Username should contain only alphabets")
	private String username;
	
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be ending with @gmail.com")
	@NotEmpty(message = "Email is required")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid Password")
	@NotEmpty(message = "Password is required")
	private String password;
}
