package com.exus.reporting.controller;

import com.exus.reporting.application.FindEmployeeReportsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeReportController.class)
// todo
class EmployeeReportControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean FindEmployeeReportsUseCase findEmployeeReportsUseCase;

  @Test
  void getEmployeeReport() {}
}
