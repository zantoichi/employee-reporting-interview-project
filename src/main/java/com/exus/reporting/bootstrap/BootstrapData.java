package com.exus.reporting.bootstrap;

import com.exus.reporting.domain.Department;
import com.exus.reporting.domain.Employee;
import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.domain.Gender;
import com.exus.reporting.domain.Priority;
import com.exus.reporting.domain.Title;
import com.exus.reporting.repository.EmployeeReportRepository;
import com.exus.reporting.repository.EmployeeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class BootstrapData {

  private final EmployeeRepository employeeRepository;
  private final EmployeeReportRepository reportRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void initData() {

    var report1 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description")
                .title("title")
                .priority(Priority.HIGH)
                .build());

    var report2 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description2")
                .title("title2")
                .priority(Priority.LOW)
                .build());
    var report3 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description3")
                .title("title4")
                .priority(Priority.LOW)
                .build());
    var report4 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description4")
                .title("title4")
                .priority(Priority.HIGH)
                .build());
    var report5 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description5")
                .title("title5")
                .priority(Priority.LOW)
                .build());
    var report6 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description6")
                .title("title6")
                .priority(Priority.LOW)
                .build());
    var report7 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description7")
                .title("title7")
                .priority(Priority.HIGH)
                .build());

    var report8 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description8")
                .title("title8")
                .priority(Priority.HIGH)
                .build());

    var report9 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description9")
                .title("title9")
                .priority(Priority.HIGH)
                .build());

    var report10 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description10")
                .title("title10")
                .priority(Priority.HIGH)
                .build());

    var report11 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description11")
                .title("title11")
                .priority(Priority.HIGH)
                .build());

    var report12 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description12")
                .title("title12")
                .priority(Priority.HIGH)
                .build());

    var report13 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description13")
                .title("title13")
                .priority(Priority.HIGH)
                .build());

    var report14 =
        reportRepository.save(
            EmployeeReport.builder()
                .description("description14")
                .title("title14")
                .priority(Priority.LOW)
                .build());

    employeeRepository.save(
        Employee.builder()
            .department(Department.CALL_CENTER)
            .email("test@email.com")
            .firstName("firstName")
            .gender(Gender.FEMALE)
            .lastName("lastname")
            .title(Title.JUNIOR)
            .userName("username")
            .reportIds(
                Set.of(report1.getReportId().orElseThrow(), report2.getReportId().orElseThrow()))
            .build());

    employeeRepository.save(
        Employee.builder()
            .department(Department.IT)
            .email("test2@email.com")
            .firstName("firstName2")
            .gender(Gender.FEMALE)
            .lastName("lastname2")
            .title(Title.SENIOR)
            .userName("username2")
            .reportIds(
                Set.of(
                    report3.getReportId().orElseThrow(),
                    report4.getReportId().orElseThrow(),
                    report5.getReportId().orElseThrow(),
                    report6.getReportId().orElseThrow(),
                    report7.getReportId().orElseThrow(),
                    report8.getReportId().orElseThrow(),
                    report9.getReportId().orElseThrow(),
                    report10.getReportId().orElseThrow(),
                    report11.getReportId().orElseThrow(),
                    report12.getReportId().orElseThrow(),
                    report13.getReportId().orElseThrow(),
                    report14.getReportId().orElseThrow()))
            .build());
  }
}
