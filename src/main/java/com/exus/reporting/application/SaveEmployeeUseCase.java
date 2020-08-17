package com.exus.reporting.application;

import com.exus.reporting.domain.Department;
import com.exus.reporting.domain.Employee;
import com.exus.reporting.domain.Gender;
import com.exus.reporting.domain.Title;
import lombok.Builder;
import lombok.Value;

public interface SaveEmployeeUseCase {

  Employee save(SaveEmployeeCommand command);

  @Value
  @Builder
  final class SaveEmployeeCommand {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String email;

    private final Gender gender;

    private final Title title;

    private final Department department;
  }
}
