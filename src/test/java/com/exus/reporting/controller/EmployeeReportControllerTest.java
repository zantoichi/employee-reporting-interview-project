package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_REPORT_URL;
import static com.exus.reporting.factory.EmployeeReportMother.defaultEmployeeReportWithId;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exus.reporting.application.FindEmployeeReportsUseCase;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeReportController.class)
class EmployeeReportControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean FindEmployeeReportsUseCase findEmployeeReportsUseCase;

  @Test
  void getEmployeeReportByUsername() throws Exception {

    // given
    final String username = "username";
    given(findEmployeeReportsUseCase.find(any(), any()))
        .willReturn(new PageImpl<>(List.of(defaultEmployeeReportWithId().build())));

    mockMvc
        .perform(get(API_REPORT_URL + "/{username}", username).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void getEmployeeReportByUsernameAndPriority() throws Exception {

    // given
    final String username = "username";
    final String priority = "low";
    given(findEmployeeReportsUseCase.find(any(), any(), any()))
        .willReturn(new PageImpl<>(List.of(defaultEmployeeReportWithId().build())));

    mockMvc
        .perform(
            get(API_REPORT_URL + "/{username}/{priority}", username, priority)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
