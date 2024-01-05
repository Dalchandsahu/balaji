package com.blogAPI.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@NotEmpty
	@Column(name = "user_Name")
	private String userName;

	@NotEmpty
	@Column(name = "user_Email")
	private String userEmail;

	@NotEmpty
	@Column(name = "user_Passwords")
	private String userPassword;

	@NotEmpty
	@Column(name = "user_About")
	private String userAbout;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<Post>();

}
