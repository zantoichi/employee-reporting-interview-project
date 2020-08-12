package com.exus.reporting.controller;

import static com.exus.reporting.configuration.ApplicationURLS.API_REPORT_URL;

import com.exus.reporting.application.FindEmployeeReportsUseCase;
import com.exus.reporting.domain.EmployeeReport;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_REPORT_URL)
@RequiredArgsConstructor
// todo use dto
final class EmployeeReportController {

  private final FindEmployeeReportsUseCase findEmployeeReportsUseCase;

  @GetMapping({"/{username}/{priority}", "/{username}"})
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<EmployeeReport>> getEmployeeReport(
      @PathVariable String username,
      @PathVariable Optional<String> priority,
      @PageableDefault Pageable pageable) {

    if (priority.isEmpty()) {
      return ResponseEntity.ok(findEmployeeReportsUseCase.find(username, pageable));
    }
    return ResponseEntity.ok(findEmployeeReportsUseCase.find(username, priority.get(), pageable));
  }
}
