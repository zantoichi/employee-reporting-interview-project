package com.exus.reporting.factory;

import static com.exus.reporting.application.SaveEmployeeUseCase.SaveEmployeeCommand.SaveEmployeeCommandBuilder;

import com.exus.reporting.application.SaveEmployeeUseCase.SaveEmployeeCommand;
import com.exus.reporting.domain.Department;
import com.exus.reporting.domain.Gender;
import com.exus.reporting.domain.Title;

public class SaveEmployeeCommandMother {

  public static SaveEmployeeCommandBuilder defaultSaveEmployeeCommand() {

    return SaveEmployeeCommand.builder()
        .department(Department.CALL_CENTER)
        .email("testemail@email.com")
        .firstName("testName")
        .gender(Gender.UNDISCLOSED)
        .lastName("testLastname")
        .title(Title.JUNIOR)
        .userName("testUsername");
  }
}
