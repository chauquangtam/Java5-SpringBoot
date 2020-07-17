package edu.poly.fpt.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.poly.fpt.models.User;
import edu.poly.fpt.reponsitories.UserReponsitory;
import edu.poly.fpt.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userService.findById(username);
		user.orElseThrow(()->new UsernameNotFoundException("Not found "+username));
		return user.map(MyUserDetails::new).get();
	}

}
