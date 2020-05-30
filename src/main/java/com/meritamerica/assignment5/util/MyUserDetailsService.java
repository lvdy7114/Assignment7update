package com.meritamerica.assignment5.util;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
=======
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.meritamerica.assignment5.models.Users;
import com.meritamerica.assignment5.repositories.AccountHolderRepository;

import com.meritamerica.assignment5.repositories.UsersRepository;

@Service("userDetailService")
//@Transactional
=======
import com.meritamerica.assignment5.models.User;
import com.meritamerica.assignment5.repositories.UserRepository;

@Service
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsersRepository userRepository;

	@Autowired
	UserRepository userRepository;
	
	
	@Override
<<<<<<< HEAD
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  //userRepository.FindByUserName(userName);
		   Optional<Users> users = userRepository.findByUsername(username);        
		            
		            return users.map(MyUserDetails::new).get();
=======
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.   //FindByUserName(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("not Found anywhere: " + userName));
		
		return user.map(MyUserDetails::new).get();
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
	}
		
		

	
	 



	}

	 
		
	
    
	














	
	
	

