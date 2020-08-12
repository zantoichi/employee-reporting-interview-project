package com.exus.reporting.domain;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public final class EmployeeReport {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID reportId;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime creationDate;

  @Builder
  public EmployeeReport(
      UUID reportId,
      String title,
      String description,
      Priority priority,
      LocalDateTime creationDate) {

    this.reportId = reportId;
    this.title = requireNonNull(title, "Title cannot be null.");
    this.description = requireNonNull(description, "Description cannot be null.");
    this.priority = requireNonNull(priority, "Priority cannot be null.");
    this.creationDate = creationDate;
  }

  public Optional<UUID> getReportId() {
    return ofNullable(reportId);
  }

  public Optional<LocalDateTime> getCreationDate() {
    return ofNullable(creationDate);
  }
}
