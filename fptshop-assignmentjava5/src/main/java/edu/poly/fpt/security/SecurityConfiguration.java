package edu.poly.fpt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.poly.fpt.service.UserService;
import edu.poly.fpt.service.UserServiceIpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

	@Autowired
	private UserServiceIpl userService;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//
//	}


 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/menu/login").permitAll()
//        .authorizeRequests()
////		.authorizeRequests().antMatchers("/admin")
////		.hasRole("ADMIN").antMatchers("/user")
////		.hasAnyRole("ADMIN", "USER")
				.antMatchers("/departs/**").authenticated()
				.antMatchers("/staffs/**").authenticated()
				.antMatchers("/records/**").authenticated()
				.antMatchers("/users/**").authenticated()
//				.antMatchers("/menu/**").authenticated()
				.antMatchers("/").permitAll()
				.and().formLogin()
//				.loginPage("/menu/login")
				.usernameParameter("username")
				.passwordParameter("password")
//		        .defaultSuccessUrl("/home", true)
//				.failureUrl("/?error")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/404");

//		http
//		.csrf().disable()
//		.authorizeRequests().antMatchers("/menu/login").permitAll()
//		.antMatchers("/departs/**").authenticated()
//		.antMatchers("/staffs/**").authenticated()
//	.antMatchers("/records/**").authenticated()
//	.antMatchers("/users/**").authenticated()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/menu/login").permitAll()
//		.and()
//		.logout().invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/menu/logout"))
////		.logoutSuccessUrl("/").permitAll()
//		.and()
//		.exceptionHandling()
//		.accessDeniedPage("/404");

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
