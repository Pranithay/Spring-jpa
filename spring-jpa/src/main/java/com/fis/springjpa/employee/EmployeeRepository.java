package com.fis.springjpa.employee;

import org.springframework.data.repository.CrudRepository;//Specifications

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}