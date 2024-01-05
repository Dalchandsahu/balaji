package com.blogAPI.Controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogAPI.PayLoad.ApiResponce;
import com.blogAPI.PayLoad.PostDto;
import com.blogAPI.PayLoad.PostResponse;
import com.blogAPI.Service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Create a new Post in database
	 * @since 1.0
	 * @param postDto
	 * @param userId
	 * @param category
	 * @return
	 * 
	 *         ******************************************
	 */
	@PostMapping("/user/{userId}/category/{category}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer category) {

		PostDto post = this.postService.createPost(postDto, userId, category);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get All Posts from Database
	 * @since 1.0
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDirection
	 * @return
	 * 
	 *         ******************************************
	 */

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

		PostResponse allPost = this.postService.getAllPost(pageSize, pageSize, sortDirection, sortDirection);

		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get Post By Id from database
	 * @since 1.0
	 * @param postId
	 * @return
	 * 
	 *         ******************************************
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	/**
	 * 
	 * @author Dalchand Sahu
	 * @apiNote To Get User by userId from database
	 * @since 1.0
	 * @param userId
	 * @return
	 * 
	 *         ******************************************
	 */

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get Category by categoryId from database
	 * @since 1.0
	 * @param categoryId
	 * @return
	 * 
	 *         ******************************************
	 */
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postByCategory = this.postService.getPostbyCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);
	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Delete Post by postId from database
	 * @since 1.0
	 * @param postId
	 * @return
	 * 
	 *         ******************************************
	 */
	@DeleteMapping("/posts/{postId}")
	public ApiResponce deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponce("successfully deleted", true);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Update Post by postId database
	 * @since 1.0
	 * @param postDto
	 * @param postId
	 * @return
	 * 
	 *         ******************************************
	 */
	@PutMapping("/Posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

}
