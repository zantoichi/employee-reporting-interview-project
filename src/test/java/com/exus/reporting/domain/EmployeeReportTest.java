package com.exus.reporting.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.exus.reporting.factory.EmployeeReportMother;
import org.junit.jupiter.api.Test;

class EmployeeReportTest {

  @Test
  void constructorValidationThrowsExceptionWhenPropertyIsMissing() {

    Throwable thrown =
        catchThrowable(
            () -> EmployeeReportMother.defaultEmployeeReportWithId().priority(null).build());

    assertThat(thrown)
        .isInstanceOf(NullPointerException.class)
        .hasMessage("Priority cannot be null.");
  }

  @Test
  void employeeIsConstructedWhenNoPropertyIsMissing() {
    EmployeeReport employeeReport = EmployeeReportMother.defaultEmployeeReportWithId().build();

    assertThat(employeeReport).isNotNull();
  }
}
