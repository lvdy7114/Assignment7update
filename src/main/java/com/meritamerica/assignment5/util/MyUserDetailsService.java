package com.meritamerica.assignment5.util;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.meritamerica.assignment5.models.Users;
import com.meritamerica.assignment5.repositories.UsersRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {	
		
		Optional<Users> users = usersRepository.findByUsername(userName);        
        
        return users.map(MyUserDetails::new).get();
	}

	
	
	
}
