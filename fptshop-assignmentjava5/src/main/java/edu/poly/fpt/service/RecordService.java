package edu.poly.fpt.service;

import java.util.List;
import java.util.Optional;

import edu.poly.fpt.models.Record;
import edu.poly.fpt.models.Staff;

public interface RecordService {

	void deleteAll();

	void deleteAll(List<Record> entities);

	void delete(Record entity);

	void deleteById(Long id);

	long count();

	List<Record> findAllById(List<Long> ids);

	List<Record> findAll();

	boolean existsById(Long id);

	Optional<Record> findById(Long id);

	List<Record> saveAll(List<Record> entities);

	Record save(Record entity);

	List<Staff> findAllStaffs();

	List<Record> findByTypeLikeOrderByTypeAsc(Integer type);

	List<Object[]> getRankHome();

	List<Object[]> getDepartsRecord();

	List<Object[]> getStaffsRecord();

}
