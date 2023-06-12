package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Spring Data JPA creates an implementation when you run the application.
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
}
