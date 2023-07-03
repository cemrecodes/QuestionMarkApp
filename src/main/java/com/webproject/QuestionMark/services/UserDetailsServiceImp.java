package com.webproject.QuestionMark.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webproject.QuestionMark.Entities.User;
import com.webproject.QuestionMark.Repos.UserRepository;
import com.webproject.QuestionMark.security.JwtUserDetails;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private UserRepository userRepository;
	
	
	public UserDetailsServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		return null;
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}
}
