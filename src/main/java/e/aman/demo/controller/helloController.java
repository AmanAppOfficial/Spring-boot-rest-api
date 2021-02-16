package e.aman.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import e.aman.demo.model.Employee;
import e.aman.demo.repository.EmployeeRepo;

@RestController
@RequestMapping("api")
public class helloController {


	@Autowired
	private EmployeeRepo empRepo; 
	
	
	/**search all employees**/
	@GetMapping(path="/employees")
	public @ResponseBody Iterable<Employee> getEmployees() {
		return empRepo.findAll();
	}
	
	
	
	
	/**search by id**/
	@GetMapping(path="employee/{id}")
	@ResponseBody
	public ResponseEntity getEmployee(@PathVariable(value="id") Integer id) {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent()) {
			return ResponseEntity.ok(emp);
		}
		
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	/**post employee**/
	@PostMapping(path="/employee")
	public Employee saveEmployees(@RequestBody Employee emp) {
		Employee empTemp = (Employee) emp;
		if(empTemp.getName()!=null) {
		return empRepo.save(emp);
		}
		return null;
	}

	
	/**put employee**/
	@PutMapping(path="/employee/{id}")
	public Employee saveEmployees(@PathVariable(value="id") Integer id , @RequestBody Employee emp) {
		Employee empTemp = (Employee) emp;
		if(empTemp.getName()!=null) {
		return empRepo.save(emp);
		}
		return null;
	}
	
	
	
/**	Dynamic query custom
	method to search by name
	**/
	
	@GetMapping(path="employee/search/{name}")
	public List<Employee> findAllById(@PathVariable(value="name") String name) {
		
		List<Employee> empList = empRepo.findAllByName(name);
		return empList;
		
	}
	
	
}
