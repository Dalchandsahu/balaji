package com.blogAPI.PayLoad;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 5, max = 20, message = "Titel must be Atleast 5 charater !!!")
	private String categoryTitel;

	@NotEmpty
	@Size(min = 5, max = 50, message = "Description must be Atleast 5 charater !!!")
	private String categoryDescription;

}
