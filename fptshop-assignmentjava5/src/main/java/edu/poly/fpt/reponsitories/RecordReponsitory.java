package edu.poly.fpt.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.models.Record;

@Repository
public interface RecordReponsitory extends CrudRepository<Record, Long> {
	List<Record> findByTypeLikeOrderByTypeAsc(Integer type);

	@Query("SELECT r.staff.id,SUM(case when r.type =1 then 1 else 0 end),SUM(case when r.type=0 then 1 else 0 end)"
			+ ", r.staff.name,SUM(case when r.type =1 then 1 else 0 end)-SUM(case when r.type=0 then 1 else 0 end) as total  from Record r  GROUP BY r.staff.id,r.staff.name order by total desc	")
	public List<Object[]> getStaffsRecord();

	@Query("SELECT r.staff.depart.id,"
			+ "SUM(case when r.type =1 then 1 else 0 end),SUM(case when r.type=0 then 1 else 0 end)"
			+ " ,  r.staff.depart.name,SUM(case when r.type =1 then 1 else 0 end) - SUM(case when r.type=0 then 1 else 0 end) as total from Record r "
			+ "  GROUP BY r.staff.depart.id ,r.staff.depart.name order by total desc	")
	public List<Object[]> getDepartsRecord();

	@Query(value = "SELECT TOP 2 s.id ID, s.name StaffName, d.name DepartName,\r\n"
			+ "	(Sum(case when r.type=1 then 1 else 0 end) - Sum(case when r.type=0 then 1 else 0 end)) total\r\n"
			+ "	FROM records r JOIN dbo.staffs s ON s.id = r.staff_id\r\n"
			+ "	JOIN dbo.departs d ON d.id = s.depart_id\r\n"
			+ "	GROUP BY s.id, s.name, d.name ORDER BY total DESC", nativeQuery = true)
	public List<Object[]> getRankHome();

}
