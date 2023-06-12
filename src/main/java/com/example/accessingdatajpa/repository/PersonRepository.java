package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @RepositoryRestResource
 * Spring Data REST uses the annotation
 * to direct Spring MVC to create RESTful endpoint at "/people".
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people") //related to Spring HATEOAS
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {

    List<Person> findByLastName(@Param("name") String name);
}
