package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavant.employeerestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
