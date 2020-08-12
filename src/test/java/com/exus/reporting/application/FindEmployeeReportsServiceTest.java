package com.exus.reporting.application;

import static com.exus.reporting.factory.EmployeeMother.defaultEmployeeWithId;
import static com.exus.reporting.factory.EmployeeReportMother.defaultEmployeeReportWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;

import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.domain.Priority;
import com.exus.reporting.repository.EmployeeReportRepository;
import com.exus.reporting.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class FindEmployeeReportsServiceTest {

  @Mock private EmployeeReportRepository reportRepository;
  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private FindEmployeeReportsService serviceUnderTest;

  @Test
  void findReportsOfUserByUsernameAndPriorityWhenPriorityAndUsernameAreCorrect() {
    // given
    final String username = "username";
    final String priority = "high";

    given(employeeRepository.findByUserName(username))
        .willReturn(Optional.of(defaultEmployeeWithId().build()));
    given(reportRepository.findAllByReportIdInAndPriority(anySet(), any(), any()))
        .willReturn(
            new PageImpl<>(
                List.of(
                    defaultEmployeeReportWithId().priority(Priority.HIGH).build(),
                    defaultEmployeeReportWithId().priority(Priority.HIGH).build())));

    // when
    Page<EmployeeReport> employeeReports =
        serviceUnderTest.find(username, priority, Pageable.unpaged());

    // then
    assertThat(employeeReports.getSize()).isEqualTo(2);
    assertThat(employeeReports.get().findFirst()).isPresent();
    assertThat(employeeReports.get().findFirst())
        .map(EmployeeReport::getPriority)
        .hasValue(Priority.HIGH);
  }

  @Test
  void findReportsOfUserByUsernameWhenUsernameIsCorrect() {
    // given
    final String username = "username";

    given(employeeRepository.findByUserName(username))
        .willReturn(Optional.of(defaultEmployeeWithId().build()));
    given(reportRepository.findAllByReportIdIn(anySet(), any()))
        .willReturn(
            new PageImpl<>(
                List.of(
                    defaultEmployeeReportWithId().priority(Priority.HIGH).build(),
                    defaultEmployeeReportWithId().priority(Priority.HIGH).build())));

    // when
    Page<EmployeeReport> employeeReports = serviceUnderTest.find(username, Pageable.unpaged());

    // then
    assertThat(employeeReports.getSize()).isEqualTo(2);
    assertThat(employeeReports.get().findFirst()).isPresent();
    assertThat(employeeReports.get().findFirst())
        .map(EmployeeReport::getPriority)
        .hasValue(Priority.HIGH);
  }

  @Test
  void throwExceptionWhenPriorityValueIsNotCorrect() {

    // given
    final String username = "username";
    final String invalidPriority = "medium";

    // when
    Throwable thrown =
        catchThrowable(() -> serviceUnderTest.find(username, invalidPriority, Pageable.unpaged()));

    // then
    assertThat(thrown)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Wrong priority value.");
  }

  @Test
  void throwExceptionWhenSearchingByUsernameAndUsernameIsNotCorrect() {

    // given
    final String username = "username";
    given(employeeRepository.findByUserName(username)).willReturn(Optional.empty());

    // when
    Throwable thrown = catchThrowable(() -> serviceUnderTest.find(username, Pageable.unpaged()));

    // then
    assertThat(thrown)
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Employee not found.");
  }

  @Test
  void throwExceptionWhenSearchingByUsernameAndPriorityAndUsernameIsNotCorrect() {

    // given
    final String username = "username";
    final String priority = "high";
    given(employeeRepository.findByUserName(username)).willReturn(Optional.empty());

    // when
    Throwable thrown =
        catchThrowable(() -> serviceUnderTest.find(username, priority, Pageable.unpaged()));

    // then
    assertThat(thrown)
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Employee not found.");
  }
}
