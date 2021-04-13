package com.app.bak.config.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.app.bak.enums.UserRole;
import com.app.bak.model.User;

//public class AppUserDetails implements UserDetails {
public class AppUserDetails {

//	private static final long serialVersionUID = 1L;
//
//	private String userName;
//	private String password;
//	private boolean enabled;
//	private List<GrantedAuthority> authorities;
//	private boolean credentialsNonExpired;
//	private boolean accountNonExpired;
//	private boolean accountNonLocked;
//
//	public AppUserDetails() {
//
//	}
//
//	public AppUserDetails(User user) {
//		this.userName = user.getUserName();
//		this.password = user.getPassword();
//		this.enabled = user.isActive();
//		this.credentialsNonExpired = !user.isCredentialsExpired();
//		this.accountNonExpired = !user.isAccountExpired();
//		this.accountNonLocked = !user.isAccountLocked();
//
//		UserRole userRole = user.getUserRole();
//		this.authorities = Arrays.asList(new SimpleGrantedAuthority(userRole.toString()));
////		Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return accountNonExpired;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return accountNonLocked;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return credentialsNonExpired;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return enabled;
//	}

}
