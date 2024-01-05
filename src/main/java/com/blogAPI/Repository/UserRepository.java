package com.blogAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogAPI.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
