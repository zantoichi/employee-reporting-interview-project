package com.exus.reporting.application;

import static com.exus.reporting.factory.EmployeeMother.defaultEmployeeWithId;
import static com.exus.reporting.factory.EmployeeReportMother.defaultEmployeeReportWithId;
import static com.exus.reporting.factory.SaveEmployeeReportCommandMother.defaultSaveEmployeeReportCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.exus.reporting.domain.Employee;
import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.repository.EmployeeReportRepository;
import com.exus.reporting.repository.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveEmployeeReportServiceTest {

  @Mock private EmployeeRepository employeeRepository;
  @Mock private EmployeeReportRepository reportRepository;
  @Captor private ArgumentCaptor<Employee> employeeArgumentCaptor;

  @InjectMocks private SaveEmployeeReportService serviceUnderTest;

  @Test
  void saveEmployeeReportWhenEmployeeExists() {
    // given
    given(employeeRepository.findByUserName(anyString()))
        .willReturn(Optional.of(defaultEmployeeWithId().build()));
    given(reportRepository.save(any(EmployeeReport.class)))
        .willReturn(defaultEmployeeReportWithId().build());

    // when
    var employeeReport = serviceUnderTest.save(defaultSaveEmployeeReportCommand().build());

    // then
    then(employeeRepository).should().save(employeeArgumentCaptor.capture());
    final var capturedEmployee = employeeArgumentCaptor.getValue();
    assertThat(employeeReport).isNotNull();
    assertThat(capturedEmployee.getReportIds()).contains(employeeReport.getReportId().get());
  }
}
