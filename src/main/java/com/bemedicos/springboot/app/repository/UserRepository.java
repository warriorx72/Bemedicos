package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.bemedicos.springboot.app.models.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	

	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String username);
	
	
	

	

}
