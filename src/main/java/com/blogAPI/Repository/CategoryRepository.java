package com.blogAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogAPI.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
