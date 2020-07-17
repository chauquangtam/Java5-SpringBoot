package edu.poly.fpt.reponsitories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.User;

@Repository
public interface UserReponsitory extends CrudRepository<User, String>{
	List<User> findByUsernameLikeOrderByUsernameAsc(String name);
	User findByUsername(String username);

}
