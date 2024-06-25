package com.jsp.osa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.osa.entity.User;
import com.jsp.osa.requestdto.UserRequest;
import com.jsp.osa.responsedto.UserResponse;

@Component
public class UserMapper 
{
	
	public User mapToUser(UserRequest userRequest, User user)
	{
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		return user;
	}
	
	public UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.isDeleted(false)
				.isEmailVerified(false)
				.userRole(user.getUserRole())
				.build();
	}
}
