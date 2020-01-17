package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public Optional<User> findByEmail(String email);
}