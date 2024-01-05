package com.blogAPI.PayLoad;

import java.util.Date;

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
public class PostDto {

	private String title;

	@NotEmpty
	@Size(min = 5, max = 20, message = "Content must be Atleast 5 charaters !!")
	private String content;

	@NotEmpty
	@Size(min = 3, max = 30, message = "ImageName must be Atleast 3 charaters !!")
	private String imageName;

	private Date addedate;
	@NotEmpty
	private CategoryDto category;

	@NotEmpty
	private UserDto user;

}
