package com.example.accessingdatajpa.payroll;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id)
        );
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable("id") Long id) {
        return employeeRepository.findById(id)
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
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id")Long id){
        employeeRepository.deleteById(id);
    }
}
