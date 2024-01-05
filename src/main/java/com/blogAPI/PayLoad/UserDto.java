package com.blogAPI.PayLoad;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Integer userId;

	@NotEmpty
	@Size(min = 3, max = 20, message = "Name must be Atleast 3 charater !!!")
	private String userName;

	@NotEmpty
	@Size(min = 7, max = 100, message = "Email must be Atleast 7 charater !!!")
	private String userEmail;

	@NotEmpty
	@Size(min = 8, max = 15, message = "Password must be Atleast 8 charater !!!")
	private String userPassword;

	@NotEmpty
	@Size(min = 10, message = "About must be Atleast 10 charater !!!")
	private String userAbout;
}
