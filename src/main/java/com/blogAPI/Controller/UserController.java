package com.blogAPI.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAPI.PayLoad.ApiResponce;
import com.blogAPI.PayLoad.UserDto;
import com.blogAPI.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Create a new User in database
	 * @since 1.0
	 * @param userDto
	 * @return
	 */

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserDto = userService.createUser(userDto);

		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get all User
	 * @since 1.0
	 * @return
	 */

	@GetMapping("/{allUser}")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAlluser());

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get User by userId from database
	 * @since 1.0
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userById = this.userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Update User By Id in Database
	 * @since 1.0
	 * @param userDto
	 * @param userId
	 * @return
	 */

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {
		UserDto updateUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Delete User By Id in database
	 * @since 1.0
	 * @param userId
	 * @return
	 */

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		this.userService.deteUser(userId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted Successfully", true), HttpStatus.OK);
	}

}
