package com.example.accessingdatajpa.payroll.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "CUSTOMER_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Status status;

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
