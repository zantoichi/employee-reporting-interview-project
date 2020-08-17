package com.exus.reporting.application;

import static com.exus.reporting.domain.Priority.valueOf;

import com.exus.reporting.domain.Employee;
import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.repository.EmployeeReportRepository;
import com.exus.reporting.repository.EmployeeRepository;
import io.vavr.control.Try;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FindEmployeeReportsService implements FindEmployeeReportsUseCase {

  private final EmployeeReportRepository reportRepository;
  private final EmployeeRepository employeeRepository;

  @Override
  public Page<EmployeeReport> find(String username, String priority, Pageable pageable) {

    var priorityEnumValue =
        Try.of(() -> valueOf(priority.toUpperCase()))
            .getOrElseThrow(() -> new IllegalArgumentException("Wrong priority value."));

    return reportRepository.findAllByReportIdInAndPriority(
        fetchEmployee(username).getReportIds(), priorityEnumValue, pageable);
  }

  @Override
  public Page<EmployeeReport> find(String username, Pageable pageable) {

    return reportRepository.findAllByReportIdIn(fetchEmployee(username).getReportIds(), pageable);
  }

  private Employee fetchEmployee(String username) {

    return employeeRepository
        .findByUserName(username)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found."));
  }
}
