package com.jsp.osa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.osa.entity.User;
import com.jsp.osa.enums.UserRole;
import com.jsp.osa.requestdto.OtpVerificationRequest;
import com.jsp.osa.requestdto.UserRequest;
import com.jsp.osa.responsedto.UserResponse;
import com.jsp.osa.service.UserService;
import com.jsp.osa.utility.ErrorStructure;
import com.jsp.osa.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController 
{
	private UserService userService;
	
	@PostMapping("/sellers/register")
	@Operation(description = "The endpoint is used to create the "
			+ "Seller in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Seller created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<UserResponse>> saveSeller(@RequestBody @Valid UserRequest userRequest)
	{
		return userService.saveUser(userRequest , UserRole.SELLER);
	}
	
	
	@PostMapping("/customers/register")
	@Operation(description = "The endpoint is used to create the "
			+ "Customer in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Customer created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<UserResponse>> saveCustomer(@RequestBody @Valid UserRequest userRequest)
	{
		return userService.saveUser(userRequest , UserRole.CUSTOMER);
	}
	
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOtp(@RequestBody @Valid OtpVerificationRequest otpVerificationRequest)
	{
		return userService.verifyOtp(otpVerificationRequest);
	}
}
