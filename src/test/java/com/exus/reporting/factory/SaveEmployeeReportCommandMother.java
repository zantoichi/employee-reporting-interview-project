package com.exus.reporting.factory;

import static com.exus.reporting.application.SaveEmployeeReportUseCase.SaveEmployeeReportCommand.SaveEmployeeReportCommandBuilder;

import com.exus.reporting.application.SaveEmployeeReportUseCase.SaveEmployeeReportCommand;
import com.exus.reporting.domain.Priority;

public class SaveEmployeeReportCommandMother {

  public static SaveEmployeeReportCommandBuilder defaultSaveEmployeeReportCommand() {

    return SaveEmployeeReportCommand.builder()
        .title("testTitle")
        .description("testDescription")
        .employeeUsername("testUsername")
        .priority(Priority.LOW);
  }
}
