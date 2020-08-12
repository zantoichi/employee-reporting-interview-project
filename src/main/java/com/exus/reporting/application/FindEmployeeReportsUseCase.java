package com.exus.reporting.application;

import com.exus.reporting.domain.EmployeeReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindEmployeeReportsUseCase {

  Page<EmployeeReport> find(String username, Pageable pageable);

  Page<EmployeeReport> find(String username, String priority, Pageable pageable);
}
