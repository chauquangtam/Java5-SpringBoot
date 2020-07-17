package edu.poly.fpt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Record;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.reponsitories.DepartReponsitory;
import edu.poly.fpt.reponsitories.RecordReponsitory;
import edu.poly.fpt.reponsitories.StaffReponsitory;
@Service
public class RecordServiceIpl implements RecordService {
	@Autowired
	private RecordReponsitory recordReponsitory;
	@Autowired
	private StaffReponsitory staffReponsitory;

	@Override
	public List<Record> findByTypeLikeOrderByTypeAsc(Integer type) {
		return recordReponsitory.findByTypeLikeOrderByTypeAsc(type);
	}
	
	@Override
	public List<Object[]> getStaffsRecord() {
		return recordReponsitory.getStaffsRecord();
	}

	@Override
	public List<Object[]> getDepartsRecord() {
		return recordReponsitory.getDepartsRecord();
	}

	@Override
	public List<Object[]> getRankHome() {
		return recordReponsitory.getRankHome();
	}

	@Override
	public List<Staff> findAllStaffs() {
		return (List<Staff>) staffReponsitory.findAll();
	}
	@Override
	public Record save(Record entity) {
		return recordReponsitory.save(entity);
	}

	@Override
	public List<Record> saveAll(List<Record> entities) {
		return (List<Record>) recordReponsitory.saveAll(entities);
	}

	@Override
	public Optional<Record> findById(Long id) {
		return recordReponsitory.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return recordReponsitory.existsById(id);
	}

	@Override
	public List<Record> findAll() {
		return (List<Record>) recordReponsitory.findAll();
	}

	@Override
	public List<Record> findAllById(List<Long> ids) {
		return (List<Record>) recordReponsitory.findAllById(ids);
	}

	@Override
	public long count() {
		return recordReponsitory.count();
	}

	@Override
	public void deleteById(Long id) {
		recordReponsitory.deleteById(id);
	}

	@Override
	public void delete(Record entity) {
		recordReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(List<Record> entities) {
		recordReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		recordReponsitory.deleteAll();
	}

}
