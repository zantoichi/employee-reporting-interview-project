package com.exus.reporting.factory;

import static com.exus.reporting.domain.EmployeeReport.EmployeeReportBuilder;

import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.domain.Priority;
import java.util.UUID;

public final class EmployeeReportMother {

  public static EmployeeReportBuilder defaultEmployeeReportWithId() {

    return EmployeeReport.builder()
        .reportId(UUID.randomUUID())
        .title("title")
        .description("desc")
        .priority(Priority.HIGH);
  }
}
