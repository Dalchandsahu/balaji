package com.blogAPI.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@NotEmpty
	@Column(name = "Post_Titel")
	private String postTitle;

	@NotEmpty
	@Column(name = "Post_Content")
	private String postContent;

	@NotEmpty
	@Column(name = "Post_ImageName")
	private String postImageName;

	@NotEmpty
	@Column(name = "Post_Date")
	private Date addDate;

	@ManyToOne
	@JoinColumn(name = "Post_Category")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "Post_User")
	private User user;

}
