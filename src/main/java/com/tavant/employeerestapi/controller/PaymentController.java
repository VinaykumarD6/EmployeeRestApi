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
import com.tavant.employeerestapi.model.Payment;
import com.tavant.employeerestapi.repository.OrderRepository;
import com.tavant.employeerestapi.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	@Autowired
	PaymentRepository paymentRepository;
	@GetMapping
	public String getPayment() {
		return "hello";
	}
	@GetMapping("/all")
	public List<Payment> getAllPayments() throws Exception{
//		return employeeRepository.findAll();
	    List list = (List) this.paymentRepository.findAll();
	    return Optional.ofNullable(list.isEmpty()?null:list).orElseThrow(()->new NoDataFoundException("No records"));
		//return Optional.ofNullable(employeeRepository.findAll()).orElseThrow(()->new NoDataFoundException("No records"));

}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Optional<Payment> optional =  paymentRepository.findById(id);
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
	public Payment addPayment(@RequestBody @Valid Payment payment) throws BlankObjectException {
		if(payment.getPaymentId()==null) {
			throw new BlankObjectException("Provide Order Object");
		}
		return paymentRepository.save(payment);
	}
	@DeleteMapping("/{id}")
	public void deletePayment(@PathVariable Integer id) {
		paymentRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public Payment updatePayment(@RequestBody @Valid Payment newPayment, @PathVariable Integer id) {
		return paymentRepository.findById(id).map(payment->{
			payment.setPaymentId(newPayment.getPaymentId());
			payment.setCheckNumber(newPayment.getCheckNumber());
			return paymentRepository.save(payment);
		}).orElseGet(()->{
			newPayment.setPaymentId(id);
			return paymentRepository.save(newPayment);
		});
		
	}

}
