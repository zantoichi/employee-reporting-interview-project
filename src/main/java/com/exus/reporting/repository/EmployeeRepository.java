package com.exus.reporting.repository;

import com.exus.reporting.domain.Employee;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

  Optional<Employee> findByUserName(String username);
}
