package com.exus.reporting.factory;

import static com.exus.reporting.domain.Employee.EmployeeBuilder;

import com.exus.reporting.domain.Department;
import com.exus.reporting.domain.Employee;
import com.exus.reporting.domain.Gender;
import com.exus.reporting.domain.Title;
import java.util.Set;
import java.util.UUID;

public final class EmployeeMother {

  public static EmployeeBuilder defaultEmployeeWithId() {
    return Employee.builder()
        .employeeId(UUID.randomUUID())
        .department(Department.CALL_CENTER)
        .email("test@email.com")
        .firstName("firstName")
        .gender(Gender.UNDISCLOSED)
        .lastName("lastname")
        .title(Title.JUNIOR)
        .userName("username")
        .reports(Set.of());
  }
}
