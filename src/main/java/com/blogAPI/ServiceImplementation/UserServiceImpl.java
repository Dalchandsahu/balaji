package com.blogAPI.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogAPI.Entity.User;
import com.blogAPI.Exception.ResourceNotFoundException;
import com.blogAPI.PayLoad.UserDto;
import com.blogAPI.Repository.UserRepository;
import com.blogAPI.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveUser = this.userRepository.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());
		
		User updateUser = this.userRepository.save(user);
		UserDto userDto1 =this.userToDto(updateUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos =users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId ));
		this.userRepository.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
		User user  = this.modelMapper.map(userDto, User.class);
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}

}
