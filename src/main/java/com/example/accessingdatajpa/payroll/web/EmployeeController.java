package com.example.accessingdatajpa.payroll.web;

import com.example.accessingdatajpa.payroll.domain.EmployeeNotFoundException;
import com.example.accessingdatajpa.payroll.domain.Employee;
import com.example.accessingdatajpa.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler assembler;

    /**
     * CollectionModel<> is another Spring HATEOAS container;
     * it’s aimed at encapsulating collections of resources—instead of a single resource entity, like EntityModel<> from earlier.
     * CollectionModel<>, too, lets you include links.
     *
     * What does "encapsulating collections" mean? Collections of employees?
     * Not quite.
     * Since we’re talking REST, it should encapsulate collections of employee resources.
     *
     * That’s why you fetch all the employees, but then transform them into a list of EntityModel<Employee> objects.
     */
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = employeeRepository
                .findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(
                employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel()
        );
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = assembler.toModel(employeeRepository.save(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * `linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel()` asks
     * : Spring HATEOAS build a link to the EmployeeController's getEmployee() method, and flag it as a self link.
     *
     * `linkTo(methodOn(EmployeeController.class).all()).withRel("employees)` asks
     * : Spring HATEOAS build a link to the aggregate root, all(), and call it "employees".
     */
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable("id") Long id) {
        Employee updatedEmployee = employeeRepository.findById(id)
                .map(
                        employee -> {
                            employee.setName(newEmployee.getName());
                            employee.setRole(newEmployee.getRole());
                            return employeeRepository.save(employee);
                        })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
