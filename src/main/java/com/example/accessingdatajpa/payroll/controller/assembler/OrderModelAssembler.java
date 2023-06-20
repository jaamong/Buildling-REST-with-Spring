package com.example.accessingdatajpa.payroll.controller.assembler;

import com.example.accessingdatajpa.payroll.controller.OrderController;
import com.example.accessingdatajpa.payroll.domain.Order;
import com.example.accessingdatajpa.payroll.domain.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {

        EntityModel<Order> orderModel = EntityModel.of(
                order,
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders")
        );

        if(order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
