package com.blogAPI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogAPI.Entity.Category;
import com.blogAPI.Entity.Post;
import com.blogAPI.Entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	


}
