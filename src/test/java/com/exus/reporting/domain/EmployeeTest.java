package com.exus.reporting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.exus.reporting.factory.EmployeeMother;
import org.junit.jupiter.api.Test;

class EmployeeTest {

  @Test
  void constructorValidationThrowsExceptionWhenPropertyIsMissing() {

    Throwable thrown =
        catchThrowable(() -> EmployeeMother.defaultEmployeeWithId().department(null).build());

    assertThat(thrown)
        .isInstanceOf(NullPointerException.class)
        .hasMessage("Department cannot be null.");
  }

  @Test
  void employeeIsConstructedWhenNoPropertyIsMissing() {
    Employee employee = EmployeeMother.defaultEmployeeWithId().build();

    assertThat(employee).isNotNull();
  }
}
