package com.exus.reporting.application;

import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.domain.Priority;
import lombok.Builder;
import lombok.Value;

public interface SaveEmployeeReportUseCase {

  EmployeeReport save(SaveEmployeeReportCommand command);

  @Value
  @Builder
  final class SaveEmployeeReportCommand {

    private final String employeeUsername;
    private final String title;
    private final String description;
    private final Priority priority;
  }
}
