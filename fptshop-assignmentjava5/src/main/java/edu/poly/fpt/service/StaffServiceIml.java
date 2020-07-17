package edu.poly.fpt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.reponsitories.DepartReponsitory;
import edu.poly.fpt.reponsitories.StaffReponsitory;

@Service
public class StaffServiceIml implements StaffService {
	@Autowired
	private StaffReponsitory staffReponsitory;
	@Autowired
	private DepartReponsitory departReponsitory;

	@Override
	public List<Staff> findByNameLikeOrderByNameAsc(String name) {
		return staffReponsitory.findByNameLikeOrderByNameAsc("%"+name+"%");
	}

	@Override
	public List<Depart> findAllDeparts() {
		return (List<Depart>) departReponsitory.findAll();
	}

	@Override
	public Staff save(Staff entity) {
		return staffReponsitory.save(entity);
	}

	@Override
	public List<Staff> saveAll(List<Staff> entities) {
		return (List<Staff>) staffReponsitory.saveAll(entities);
	}

	@Override
	public Optional<Staff> findById(Long id) {
		return staffReponsitory.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return staffReponsitory.existsById(id);
	}

	@Override
	public List<Staff> findAll() {
		return (List<Staff>) staffReponsitory.findAll();
	}

	@Override
	public List<Staff> findAllById(List<Long> ids) {
		return (List<Staff>) staffReponsitory.findAllById(ids);
	}

	@Override
	public long count() {
		return staffReponsitory.count();
	}

	@Override
	public void deleteById(Long id) {
		staffReponsitory.deleteById(id);
	}

	@Override
	public void delete(Staff entity) {
		staffReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(List<Staff> entities) {
		staffReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		staffReponsitory.deleteAll();
	}

}
