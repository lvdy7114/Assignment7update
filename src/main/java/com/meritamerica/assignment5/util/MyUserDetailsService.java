package com.meritamerica.assignment5.util;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.meritamerica.assignment5.models.Users;
import com.meritamerica.assignment5.repositories.UsersRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {
	/*
	 * retrieve the user's authentication and authorization information.
	 * has a single read-only method named as loadUserByUsername() 
	 * which locate the user based on the username.
	 */

	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {	
		//Optional<Users> users = usersRepository.findByUsername(userName);        
        //return users.map(MyUserDetails::new).get();
		//return new User ("admin", "admin" , new ArrayList<>());
		/*
		Optional<Users> u = usersRepository.findByUsername(userName); 		
		if(u != null) {

			log.info("super happy fun time times again");
			return new User(userName, u.getPassword(), new ArrayList<>());
		}
		log.info("super happy fun time times again again(this is the bad one)");
		return new User("admin", "admin", new ArrayList<>());
	*/
		
		Optional<Users> users =  usersRepository.findByUsername( userName);
		
		users.orElseThrow(() -> new UsernameNotFoundException("Not found"+ userName));
		return users.map(MyUserDetails :: new).get();
        
       
        
	}
	


	
	
}	

