package com.jsp.osa.service;

import org.springframework.http.ResponseEntity;

import com.jsp.osa.enums.UserRole;
import com.jsp.osa.requestdto.OtpVerificationRequest;
import com.jsp.osa.requestdto.UserRequest;
import com.jsp.osa.responsedto.UserResponse;
import com.jsp.osa.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService 
{

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid UserRequest userRequest, UserRole userRole);

	ResponseEntity<ResponseStructure<UserResponse>> verifyOtp(OtpVerificationRequest otpVerificationRequest);

}
