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
import com.tavant.employeerestapi.model.Customer;
import com.tavant.employeerestapi.model.Employee;
import com.tavant.employeerestapi.model.Order;
import com.tavant.employeerestapi.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	@GetMapping
	public String getCustomer() {
		return "hello";
	}
	@GetMapping("/all")
	public List<Customer> getAllCustomers() throws Exception{
//		return employeeRepository.findAll();
	    List list = (List) this.customerRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
		//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));

}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Optional<Customer> optional =  customerRepository.findById(id);
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
	public Customer addCustomer(@RequestBody @Valid Customer customer) throws BlankObjectException {
		if(customer.getCustomerNumber()==null) {
			throw new BlankObjectException("Provide Customer Object");
		}
		return customerRepository.save(customer);
	}
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody @Valid Customer newCustomer, @PathVariable Integer id) {
		return customerRepository.findById(id).map(customer->{
			customer.setCustomerNumber(newCustomer.getCustomerNumber());
			customer.setCustomerName(newCustomer.getCustomerName());
			return customerRepository.save(customer);
		}).orElseGet(()->{
			newCustomer.setCustomerNumber(id);
			return customerRepository.save(newCustomer);
		});
		
	}

}
