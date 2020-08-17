package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_EMPLOYEES_URL;

import com.exus.reporting.application.SaveEmployeeUseCase;
import com.exus.reporting.application.SaveEmployeeUseCase.SaveEmployeeCommand;
import com.exus.reporting.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_EMPLOYEES_URL)
@RequiredArgsConstructor
final class SaveEmployeeController {

  private final SaveEmployeeUseCase saveEmployeeUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Employee> saveEmployee(@RequestBody SaveEmployeeCommand command) {

    return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployeeUseCase.save(command));
  }
}
