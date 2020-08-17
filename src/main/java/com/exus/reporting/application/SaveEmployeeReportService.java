package com.exus.reporting.application;

import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.repository.EmployeeReportRepository;
import com.exus.reporting.repository.EmployeeRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SaveEmployeeReportService implements SaveEmployeeReportUseCase {

  private final EmployeeRepository employeeRepository;
  private final EmployeeReportRepository reportRepository;

  @Override
  @Transactional
  public EmployeeReport save(SaveEmployeeReportCommand command) {

    var employee =
        employeeRepository
            .findByUserName(command.getEmployeeUsername())
            .orElseThrow(() -> new EntityNotFoundException("Employee not found."));

    var employeeReport = reportRepository.save(EmployeeReport.fromCommand(command));
    employee.addEmployeeReport(employeeReport.getReportId().get());

    employeeRepository.save(employee);

    return employeeReport;
  }
}
