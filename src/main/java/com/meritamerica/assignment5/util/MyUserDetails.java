package com.meritamerica.assignment5.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.meritamerica.assignment5.models.Users;

public class MyUserDetails implements UserDetails{
	
	

	private String username;
=======
import org.springframework.security.core.userdetails.UserDetails;

import com.meritamerica.assignment5.models.User;

public class MyUserDetails implements UserDetails {
	

	private String userName;
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

<<<<<<< HEAD
	
	   public MyUserDetails(Users users) {   
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.active = users.isActive();
        this.authorities = Arrays.stream(users.getRoles().split("'"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    } 
	 
=======
	public MyUserDetails(User user) {	
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split("'"))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}	
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
	
	public MyUserDetails() {		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
<<<<<<< HEAD
		
=======
		// TODO Auto-generated method stub
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
		return authorities;
	}

	@Override
	public String getPassword() {
<<<<<<< HEAD
		
=======
		// TODO Auto-generated method stub
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
		return password;
	}

	@Override
	public String getUsername() {
<<<<<<< HEAD
	
		return username;
=======
		// TODO Auto-generated method stub
		return userName;
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
	}

	@Override
	public boolean isAccountNonExpired() {
<<<<<<< HEAD
		
=======
		// TODO Auto-generated method stub
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
<<<<<<< HEAD
	
=======
		// TODO Auto-generated method stub
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
<<<<<<< HEAD
	
=======
		// TODO Auto-generated method stub
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
		return true;
	}

	@Override
	public boolean isEnabled() {
<<<<<<< HEAD
		
		return active;
	}

}
=======
		// TODO Auto-generated method stub
		return active;
	}
	
	
}	
>>>>>>> 7ab9cc7667690d1d5d1f6e90e5e5d7d968e6978e
