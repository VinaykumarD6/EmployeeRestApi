package com.tavant.employeerestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.employeerestapi.exception.BlankObjectException;
import com.tavant.employeerestapi.exception.EmployeeNotFoundException;
import com.tavant.employeerestapi.exception.NoDataFoundException;
import com.tavant.employeerestapi.model.Employee;
import com.tavant.employeerestapi.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	@GetMapping
	public String getEmployee() {
		return "hello";
	}
	@GetMapping("/all")
	public List<Employee> getAllEmployees() throws Exception{
//		return employeeRepository.findAll();
    List list = this.employeeRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
	//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));

	}
	/*@GetMapping("/all")
	public List<Employee> getAllEmployees() throws Exception{
//		return employeeRepository.findAll();
	    //List list = this.employeeRepository.findAll();
	    //return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
		//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));
		Optional<List<Employee>> optional =  Optional.ofNullable(employeeRepository.findAll());
		if(optional.isPresent())
		{
			return (List<Employee>) ResponseEntity.ok(optional);
		}else {
			throw new NoDataFoundException("no records");
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmployeeNotFoundException("record not found"));
		}

	}*/

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Optional<Employee> optional =  employeeRepository.findById(id);
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}else {
			throw new EmployeeNotFoundException("not found");
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmployeeNotFoundException("record not found"));
		}
		//return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("record not forund"));		

	}
	@PostMapping
	public Employee addEmployee(@RequestBody @Valid Employee employee) throws BlankObjectException {
		if(employee.getEmployeeNumber()==null) {
			throw new BlankObjectException("Provide Employee Object");
		}
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody @Valid Employee newEmployee, @PathVariable Integer id) {
		return employeeRepository.findById(id).map(employee->{
			employee.setFirstName(newEmployee.getFirstName());
			employee.setLastName(newEmployee.getLastName());
			return employeeRepository.save(employee);
		}).orElseGet(()->{
			newEmployee.setEmployeeNumber(id);
			return employeeRepository.save(newEmployee);
		});		
	}
}
