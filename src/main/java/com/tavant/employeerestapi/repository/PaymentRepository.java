package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
