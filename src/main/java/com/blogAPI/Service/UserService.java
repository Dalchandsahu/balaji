package com.blogAPI.Service;

import java.util.List;

import com.blogAPI.PayLoad.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAlluser();
	
	void deteUser(Integer userId);
	
	
}
