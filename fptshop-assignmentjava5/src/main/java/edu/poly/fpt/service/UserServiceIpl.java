package edu.poly.fpt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.fpt.models.User;
import edu.poly.fpt.reponsitories.UserReponsitory;

@Service
public class UserServiceIpl implements UserService, UserDetailsService {
	@Autowired
	private UserReponsitory userReponsitory;
	@Autowired
	private HttpSession session ;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userReponsitory.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
//		session.setAttribute("username", user.getUsername());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities); 	
	}

	@Override
	public User save(User entity) {
		return userReponsitory.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>) userReponsitory.saveAll(entities);
	}

	@Override
	public Optional<User> findById(String id) {
		return userReponsitory.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return userReponsitory.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userReponsitory.findAll();
	}

	@Override
	public List<User> findAllById(List<String> ids) {
		return (List<User>) userReponsitory.findAllById(ids);
	}

	@Override
	public long count() {
		return userReponsitory.count();
	}

	@Override
	public void deleteById(String id) {
		userReponsitory.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userReponsitory.deleteAll();
	}

	@Override
	public boolean checkLogin(String username, String password) {
		Optional<User> op = findById(username);
		if (op.isPresent() && op.get().getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> findByUsernameLikeOrderByUsernameAsc(String name) {
		return userReponsitory.findByUsernameLikeOrderByUsernameAsc("%" + name + "%");
	}

}
