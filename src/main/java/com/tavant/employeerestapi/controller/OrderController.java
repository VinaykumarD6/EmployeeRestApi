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
import com.tavant.employeerestapi.model.Order;
import com.tavant.employeerestapi.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	@GetMapping
	public String getOrder() {
		return "hello";
	}
	@GetMapping("/all")
	public List<Order> getAllOrders() throws Exception{
//		return employeeRepository.findAll();
	    List list = (List) this.orderRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
		//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));

}

	
	private Object findAll() {
	// TODO Auto-generated method stub
	return null;
}
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Optional<Order> optional =  orderRepository.findById(id);
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
	public Order addOrder(@RequestBody @Valid Order order) throws BlankObjectException {
		if(order.getOrderId()==null) {
			throw new BlankObjectException("Provide Order Object");
		}
		return orderRepository.save(order);
	}
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Integer id) {
		orderRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public Order updateOrder(@RequestBody @Valid Order newOrder, @PathVariable Integer id) {
		return orderRepository.findById(id).map(order->{
			order.setOrderId(newOrder.getOrderId());
			order.setCustomerId(newOrder.getStatus());
			return orderRepository.save(order);
		}).orElseGet(()->{
			newOrder.setOrderId(id);
			return orderRepository.save(newOrder);
		});
		
	}

}
