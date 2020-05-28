package com.meritamerica.assignment5.util;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	//	new User("Meryem", "Meryem", new ArrayList<>());
		//Change this to real DB info(sa, password for h2)
		return new User("sa", "password", new ArrayList<>());
	}

	
	
	
}
