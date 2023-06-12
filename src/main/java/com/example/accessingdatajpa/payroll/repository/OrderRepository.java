package com.example.accessingdatajpa.payroll.repository;

import com.example.accessingdatajpa.payroll.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
