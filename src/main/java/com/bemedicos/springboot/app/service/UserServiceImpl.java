package com.bemedicos.springboot.app.service;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bemedicos.springboot.app.dto.ChangePasswordForm;
import com.bemedicos.springboot.app.exception.CustomeFieldValidationException;
import com.bemedicos.springboot.app.models.entity.User;
import com.bemedicos.springboot.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}
	
	private boolean checkEmailAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByEmail(user.getEmail());
		if (userFound.isPresent()) {
			throw new CustomeFieldValidationException("Correo no disponible","email");
		}//End_if
		return true;
	}//End_Exception
	
private boolean checkUsernameAvailable(User user) throws Exception{
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new CustomeFieldValidationException("Usuario no disponible","username");
		}
		return true;
	}
	
	
	
	private boolean checkPasswordValid(User user) throws Exception {
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmPassword");
		}//End_if
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new CustomeFieldValidationException("Password y Confirm Password no son iguales","password");
		}//End_if
		return true;
	}//End_Exception
	
	@Override
	public User createUser(User user) throws Exception {
		if (checkEmailAvailable(user) && checkUsernameAvailable(user) &&  checkPasswordValid(user) || user.getId()>0 || user.getId()!=null) {
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			user = repository.save(user);
		}
		return user;
	}
	@Override
	public User getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe."));
	}
	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}
	
	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setEmail(from.getEmail());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setUsername(from.getUsername());
		///to.setRoles(from.getRoles());
		to.setId(from.getId());
	}
	
	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user = getUserById(form.getId());

		if ( !user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception ("Current Password invalido.");
		}
		
		if( user.getPassword().equals(form.getNewPassword())) {
			throw new Exception ("Nuevo debe ser diferente al password actual.");
		}
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception ("Nuevo Password y Current Password no coinciden.");
		}
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setPassword(encodePassword);
		return repository.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getIdDoc(String usuarioDoctor) {
		return em.createNativeQuery("select medico_id from user_med where user_medname='"+usuarioDoctor+"' or email='"+usuarioDoctor+"'").getResultList();
	}

	
	

}
