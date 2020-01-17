package com.bemedicos.springboot.app.service;



import com.bemedicos.springboot.app.dto.ChangePasswordForm;
import com.bemedicos.springboot.app.models.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;
	
	
	
	public User getUserById(Long id) throws Exception;

	public User updateUser(User user) throws Exception;
	
	public User changePassword(ChangePasswordForm form) throws Exception;

	

}

