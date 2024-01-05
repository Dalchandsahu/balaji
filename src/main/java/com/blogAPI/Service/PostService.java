package com.blogAPI.Service;

import java.util.List;

import com.blogAPI.PayLoad.PostDto;
import com.blogAPI.PayLoad.PostResponse;

public interface PostService {

	// Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// GetAll Post
	PostResponse getAllPost(Integer pageNuber, Integer pageSize, String sortBy, String sortDirection);

	// get single Post
	PostDto getPostById(Integer postId);

	// Get All Category
	List<PostDto> getPostbyCategory(Integer categoryId);

	// Get Post By Id
	List<PostDto> getPostByUser(Integer userid);

	// search posts
	List<PostDto> searchPosts(String keywords);

	
}
