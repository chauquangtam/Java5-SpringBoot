package edu.poly.fpt.reponsitories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.poly.fpt.models.Staff;

@Repository
public interface StaffReponsitory extends CrudRepository<Staff, Long> {
	List<Staff> findByNameLikeOrderByNameAsc(String name);
}
