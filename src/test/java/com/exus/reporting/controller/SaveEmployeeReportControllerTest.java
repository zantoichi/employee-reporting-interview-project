package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_REPORTS_URL;
import static com.exus.reporting.factory.EmployeeReportMother.defaultEmployeeReportWithId;
import static com.exus.reporting.factory.SaveEmployeeReportCommandMother.defaultSaveEmployeeReportCommand;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exus.reporting.application.SaveEmployeeReportUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SaveEmployeeReportController.class)
class SaveEmployeeReportControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean SaveEmployeeReportUseCase saveEmployeeReportUseCase;

  @Test
  void saveEmployee() throws Exception {

    // given
    given(saveEmployeeReportUseCase.save(any())).willReturn(defaultEmployeeReportWithId().build());

    mockMvc
        .perform(
            post(API_REPORTS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(defaultSaveEmployeeReportCommand().build()))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }
}
