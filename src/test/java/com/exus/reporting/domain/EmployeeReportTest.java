package com.exus.reporting.domain;

import static com.exus.reporting.factory.EmployeeReportMother.defaultEmployeeReportWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class EmployeeReportTest {

  @Test
  void constructorValidationThrowsExceptionWhenPropertyIsMissing() {

    Throwable thrown = catchThrowable(() -> defaultEmployeeReportWithId().priority(null).build());

    assertThat(thrown)
        .isInstanceOf(NullPointerException.class)
        .hasMessage("Priority cannot be null.");
  }

  @Test
  void employeeIsConstructedWhenNoPropertyIsMissing() {
    EmployeeReport employeeReport = defaultEmployeeReportWithId().build();

    assertThat(employeeReport).isNotNull();
  }
}
