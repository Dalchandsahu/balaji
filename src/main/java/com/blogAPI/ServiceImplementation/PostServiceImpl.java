package com.blogAPI.ServiceImplementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogAPI.Entity.Category;
import com.blogAPI.Entity.Post;
import com.blogAPI.Entity.User;
import com.blogAPI.Exception.ResourceNotFoundException;
import com.blogAPI.PayLoad.PostDto;
import com.blogAPI.PayLoad.PostResponse;
import com.blogAPI.Repository.CategoryRepository;
import com.blogAPI.Repository.PostRepository;
import com.blogAPI.Repository.UserRepository;
import com.blogAPI.Service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "PostId", postId));
		post.setPostTitle(postDto.getTitle());
		post.setPostContent(postDto.getContent());
		post.setPostImageName(postDto.getImageName());

		Post updatePost = this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "PostId", postId));
		this.postRepository.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNuber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		PageRequest p = PageRequest.of(pageNuber, pageSize,sort);
		
Page<Post> pagePost = this.postRepository.findAll(p);
		
		List<Post> allPost = pagePost.getContent();
		
		List<PostDto> postDtos = allPost.stream().map((post) ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostbyCategory(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(cat);

		List<PostDto> postDto = posts.stream().map((post) -> new ModelMapper().map(posts, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userid) {
		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user", "Id", userid));
		List<Post> posts = this.postRepository.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keywords) {
		// TODO Auto-generated method stub
		return null; 
		
	}

}
