package com.tavant.employeerestapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.Customer;

@Repository 
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
