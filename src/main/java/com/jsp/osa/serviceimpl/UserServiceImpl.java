package com.jsp.osa.serviceimpl;

import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.jsp.osa.entity.Customer;
import com.jsp.osa.entity.Seller;
import com.jsp.osa.entity.User;
import com.jsp.osa.enums.UserRole;
import com.jsp.osa.mapper.UserMapper;
import com.jsp.osa.repository.UserRepository;
import com.jsp.osa.requestdto.OtpVerificationRequest;
import com.jsp.osa.requestdto.UserRequest;
import com.jsp.osa.responsedto.UserResponse;
import com.jsp.osa.service.UserService;
import com.jsp.osa.utility.MessageData;
import com.jsp.osa.utility.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final Cache<String, User> userCache;

	private final Cache<String, String> otpCache;

	private final Random random;

	private final MailService mailService;


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid UserRequest userRequest, UserRole userRole) 
	{
		User user = userMapper.mapToUser(userRequest, new User());

		if (userRole == UserRole.SELLER) 
		{
			user = new Seller();
		} 
		else if(userRole == UserRole.CUSTOMER) 
		{
			user = new Customer();
		} 


		user = userMapper.mapToUser(userRequest, user);
		userCache.getIfPresent(user.getEmail());
		userCache.put(user.getEmail(), user);
		otpCache.getIfPresent(user.getEmail());
		String otp = String.valueOf(random.nextInt(100000, 999999));
		otpCache.put(user.getEmail(), otp);
		MessageData messageData = new MessageData();
		messageData.setTo(user.getEmail());
		messageData.setSubject("OTP for Registration");
		messageData.setText("Your OTP is: " + otpCache.getIfPresent(user.getEmail()));
		messageData.setSentDate(new Date(System.currentTimeMillis()));


		user.setUserRole(userRole);
		user = userRepository.save(user);


		try 
		{
			mailService.sendMail(messageData);
		} 
		catch (MessagingException e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<UserResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("User created")
						.setData(userMapper.mapToUserResponse(user)));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOtp(OtpVerificationRequest otpVerificationRequest) 
	{
		System.out.println(otpVerificationRequest.getEmail());
		User user = userCache.getIfPresent(otpVerificationRequest.getEmail());
		System.out.println(user);
		String otp = otpCache.getIfPresent(otpVerificationRequest.getEmail());
		System.out.println(otp);
		return null;
	}



}
