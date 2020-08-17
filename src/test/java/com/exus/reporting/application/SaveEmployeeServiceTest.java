package com.exus.reporting.application;

import static com.exus.reporting.factory.EmployeeMother.defaultEmployeeWithId;
import static com.exus.reporting.factory.SaveEmployeeCommandMother.defaultSaveEmployeeCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.exus.reporting.domain.Employee;
import com.exus.reporting.repository.EmployeeRepository;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveEmployeeServiceTest {

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private SaveEmployeeService serviceUnderTest;

  @Test
  void saveEmployeeWhenUsernameIsAvailable() {

    // given
    given(employeeRepository.findByUserName(anyString())).willReturn(Optional.empty());
    given(employeeRepository.save(any(Employee.class))).willReturn(defaultEmployeeWithId().build());

    // when
    Employee employee = serviceUnderTest.save(defaultSaveEmployeeCommand().build());

    // then
    assertThat(employee).isNotNull();
  }

  @Test
  void throwExceptionWhenUsernameIsUsed() {

    // given
    given(employeeRepository.findByUserName(anyString()))
        .willReturn(Optional.of(defaultEmployeeWithId().build()));

    // when
    Throwable thrown =
        catchThrowable(() -> serviceUnderTest.save(defaultSaveEmployeeCommand().build()));

    // then
    assertThat(thrown)
        .isInstanceOf(EntityExistsException.class)
        .hasMessage("An Employee with that name already exists.");
  }
}
