package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_EMPLOYEES_URL;
import static com.exus.reporting.factory.EmployeeMother.defaultEmployeeWithId;
import static com.exus.reporting.factory.SaveEmployeeCommandMother.defaultSaveEmployeeCommand;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exus.reporting.application.SaveEmployeeUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SaveEmployeeController.class)
class SaveEmployeeControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean SaveEmployeeUseCase saveEmployeeUseCase;

  @Test
  void saveEmployee() throws Exception {

    // given
    given(saveEmployeeUseCase.save(any())).willReturn(defaultEmployeeWithId().build());

    mockMvc
        .perform(
            post(API_EMPLOYEES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultSaveEmployeeCommand().build()))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }
}
