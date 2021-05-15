package com.app.bak.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration // no need, @EnableWebSecurity is already annotated with @Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login/authenticate").permitAll()
		.antMatchers("/user/**").hasRole("ADMIN")
		.antMatchers("/employee/**").hasAnyRole("ADMIN", "USER")
		.anyRequest().authenticated().and().httpBasic();
//      .and().formLogin();

	}

	// InMemory and JDBC Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		In memory authentication
		auth.inMemoryAuthentication().withUser("anil").password("anil").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");

		// JDBC Authentication
//		auth.jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery("select user_name as username, password, active_flag as enabled from user where user_name=?")
//        .authoritiesByUsernameQuery("select  user_name as username, user_role as role, active_flag as enabled from user where user_name=?");
//		
		// Spring Boot authentication - Not worked
//		auth.userDetailsService(userDetailsService);
	}

}
