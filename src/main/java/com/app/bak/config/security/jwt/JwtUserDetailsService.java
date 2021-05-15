package com.app.bak.config.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.bak.model.User;
import com.app.bak.service.UserService;

@Service("jwtUDService")
public class JwtUserDetailsService  implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		JWTUserDetails myUserDetails = new JWTUserDetails(user);
		return myUserDetails;
	}

}
