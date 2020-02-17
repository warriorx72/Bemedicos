package com.bemedicos.springboot.app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

////import com.medicos.springboot.app.models.entity.Role;
import com.bemedicos.springboot.app.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository repository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		com.medicos.springboot.app.models.entity.User appUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login username or email ivalidos"));
//		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
//		for(Role role: appUser.getRoles()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
//			grantList.add(grantedAuthority);
//		}
//		UserDetails user = (UserDetails) new User (username,appUser.getPassword(),grantList);
//	
//		return user;
//	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.contains("@")) {

			com.bemedicos.springboot.app.models.entity.User appUser = repository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Login username or email ivalidos"));
			int status=(appUser.getUser_med_status());
			if (status == 0 || status != 1) {
				System.out.println("adios");
				System.out.println(status);
				throw new UsernameNotFoundException("Usuario no activo.");

			}
			Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
			UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grantList);
			return user;
		} else {
			com.bemedicos.springboot.app.models.entity.User appUser = repository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Login username or email ivalidos"));
			int status=(appUser.getUser_med_status());
			if (status == 0 || status != 1) {
				System.out.println("adios");
				System.out.println(status);
				throw new UsernameNotFoundException("Usuario no activo.");

			}
			Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
			UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grantList);
			return user;

		}

		/*
		 * for(Role role: appUser.getRoles()) { GrantedAuthority grantedAuthority = new
		 * SimpleGrantedAuthority(role.getDescription());
		 * grantList.add(grantedAuthority); }
		 */

	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		com.medicos.springboot.app.models.entity.User appUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login Username Invalido."));
//
//		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
//		for (Role role: appUser.getRoles()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
//            grantList.add(grantedAuthority);
//		}
//		UserDetails user = (UserDetails) new User (username,appUser.getPassword(),grantList);
//
//		return user;
//	}
}
