package com.meritamerica.assignment5.util;

import java.util.ArrayList;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment5.models.Users;
import com.meritamerica.assignment5.repositories.AccountHolderRepository;

import com.meritamerica.assignment5.repositories.UsersRepository;

@Service("userDetailService")
//@Transactional
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  //userRepository.FindByUserName(userName);
		   Optional<Users> users = userRepository.findByUsername(username);        
		            
		            return users.map(MyUserDetails::new).get();
	}
		
		

	
	 



	}

	 
		
	
    
	














	
	
	

