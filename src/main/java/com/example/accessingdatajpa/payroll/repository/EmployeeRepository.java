package com.example.accessingdatajpa.payroll.repository;

import com.example.accessingdatajpa.payroll.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}
