package com.exus.reporting.repository;

import com.exus.reporting.domain.EmployeeReport;
import com.exus.reporting.domain.Priority;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReportRepository extends JpaRepository<EmployeeReport, UUID> {

  Page<EmployeeReport> findAllByReportIdIn(Set<UUID> reportIds, Pageable pageable);

  Page<EmployeeReport> findAllByReportIdInAndPriority(
      Set<UUID> reportIds, Priority priority, Pageable pageable);
}
