package com.app.bak.config.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {

//	@Autowired
//	UserDetailsService userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.cors().disable()
//		.csrf().disable()
////		.authorizeRequests()
//		.authorizeRequests()
//		.antMatchers("/login/authenticate").hasRole("")
//		.antMatchers("/user/**").hasRole("ADMIN")
//		.antMatchers("/employee/**").hasAnyRole("ADMIN", "USER")
//		.antMatchers("/login/**").permitAll()
//		.and().formLogin().and().logout()
//				.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
//
//	}
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

}
