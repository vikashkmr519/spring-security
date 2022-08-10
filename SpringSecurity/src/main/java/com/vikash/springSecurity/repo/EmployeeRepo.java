package com.vikash.springSecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikash.springSecurity.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByUsername(String username);
}
