package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_REPORTS_URL;

import com.exus.reporting.application.SaveEmployeeReportUseCase;
import com.exus.reporting.application.SaveEmployeeReportUseCase.SaveEmployeeReportCommand;
import com.exus.reporting.domain.EmployeeReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_REPORTS_URL)
@RequiredArgsConstructor
final class SaveEmployeeReportController {

  private final SaveEmployeeReportUseCase saveEmployeeReportUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<EmployeeReport> saveEmployeeReport(
      @RequestBody SaveEmployeeReportCommand command) {

    return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployeeReportUseCase.save(command));
  }
}
