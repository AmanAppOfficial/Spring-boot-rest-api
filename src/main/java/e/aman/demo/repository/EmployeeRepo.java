package e.aman.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import e.aman.demo.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee , Integer>{

	@Query("Select e FROM Employee e where e.name = ?1")
	public List<Employee> findAllByName(String name);
	
}
