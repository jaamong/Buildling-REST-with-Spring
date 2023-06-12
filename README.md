<h1>Spring Data JPA & RESTful</h1>
<br>

<h2>Spring Data JPA 연습 코드</h2>
<h3> Customer </h3>
* ..entity.`Customer.java`
* ..repository.`CustomerRepository.java` //only extends CrudRepository<T,ID>

<br>

---

<h2>Spring Data JPA 연습 코드 2 (with HATEOAS)</h2>
<h3> Person </h3>
* ..entity.`Person.java`
* ..repository.`PersonRepository.java` //extends CrudRepository<T,ID>, PagingAndSortingRepository<T,ID>

<br>

---

<h2>REST & Spring Data JPA 연습 코드 3 (with Spring HATEOAS)</h2>
<h3>package payroll - Employee</h3>
* `Employee.java` //entity
* `EmployeeRepository.java` //extends CrudRepository<T,ID>
* `EmployeeController.java` //controller
* `EmployeeModelAssembler` //implements RepresentationModelAssembler<T,D>, simply make links
* `LoadDatabase.java` //configuration
* `EmployeeNotFoundException.java` //extends RuntimeException
* `EmployeeNotFoundAdvice.java` //advice, Employee exception handler

<br>

---

<h2>REST & Spring Data JPA 연습 코드 4 (with Spring HATEOAS)</h2>
<h3>package payroll - Order</h3>
* `Order.java` //entity
* `Status.java` //enum
* `OrderRepository.java` //extends JpaRepository<T,ID>
* `OrderController.java` //controller
* `OrderModelAssembler` //implements RepresentationModelAssembler<T,D>, simply make links
* `LoadDatabases.java` //configuration
* `OrderNotFoundException.java` //extends RuntimeException

<br><br>

