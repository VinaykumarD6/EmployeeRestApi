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
import com.tavant.employeerestapi.model.Order;
import com.tavant.employeerestapi.model.OrderDetail;
import com.tavant.employeerestapi.repository.OrderDetailRepository;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@GetMapping
	public String getOrderDetail() {
		return "hello";
	}
	@GetMapping("/all")
	public List<OrderDetail> getAllOrderDetails() throws Exception{
//		return employeeRepository.findAll();
	    List list = (List) this.orderDetailRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
		//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));

}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderDetailById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Optional<OrderDetail> optional =  orderDetailRepository.findById(id);
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
	public OrderDetail addOrderDetail(@RequestBody @Valid OrderDetail orderDetail) throws BlankObjectException {
		if(orderDetail.getOrderDetailId()==null) {
			throw new BlankObjectException("Provide Order Object");
		}
		return orderDetailRepository.save(orderDetail);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrderDetail(@PathVariable Integer id) {
		orderDetailRepository.deleteById(id);
	}
	/*@PutMapping("/{id}")
	public OrderDetail updateCustomer(@RequestBody @Valid Customer newCustomer, @PathVariable Integer id) {
		return customerRepository.findById(id).map(customer->{
			customer.setCustomerNumber(newCustomer.getCustomerNumber());
			customer.setCustomerName(newCustomer.getCustomerName());
			return customerRepository.save(customer);
		}).orElseGet(()->{
			newCustomer.setCustomerNumber(id);
			return customerRepository.save(newCustomer);
		});*/
		
	}

}
