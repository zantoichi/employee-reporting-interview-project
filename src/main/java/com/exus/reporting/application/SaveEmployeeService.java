package com.exus.reporting.application;

import com.exus.reporting.domain.Employee;
import com.exus.reporting.repository.EmployeeRepository;
import javax.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SaveEmployeeService implements SaveEmployeeUseCase {

  private final EmployeeRepository employeeRepository;

  @Override
  public Employee save(SaveEmployeeCommand command) {

    if (employeeRepository.findByUserName(command.getUserName()).isPresent()) {
      throw new EntityExistsException("An Employee with that name already exists.");
    }

    return employeeRepository.save(Employee.fromCommand(command));
  }
}
