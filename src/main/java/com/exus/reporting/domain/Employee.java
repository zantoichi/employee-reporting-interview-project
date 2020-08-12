package com.exus.reporting.domain;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElseGet;
import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.persistence.ElementCollection;
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
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public final class Employee {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID employeeId;

  private String firstName;
  private String lastName;
  private String userName;
  private String email;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  private Title title;

  @Enumerated(EnumType.STRING)
  private Department department;

  @ElementCollection private Set<UUID> reportIds;

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime creationDate;

  @Builder
  private Employee(
      UUID employeeId,
      String firstName,
      String lastName,
      String userName,
      String email,
      Gender gender,
      Title title,
      Department department,
      Set<UUID> reports,
      LocalDateTime creationDate) {

    this.creationDate = creationDate;
    this.employeeId = employeeId;
    this.firstName = requireNonNull(firstName, "First name cannot be null.");
    this.lastName = requireNonNull(lastName, "Last name cannot be null.");
    this.userName = requireNonNull(userName, "Username cannot be null.");
    this.email = requireNonNull(email, "Email cannot be null.");
    this.gender = requireNonNull(gender, "Gender cannot be null.");
    this.title = requireNonNull(title, "Title cannot be null.");
    this.department = requireNonNull(department, "Department cannot be null.");
    this.reportIds = requireNonNullElseGet(reports, HashSet::new);
  }

  public Optional<UUID> getEmployeeId() {
    return ofNullable(employeeId);
  }

  public Set<UUID> getReports() {
    return Set.copyOf(reportIds);
  }

  public boolean addEmployeeReport(UUID reportId) {
    return reportIds.add(reportId);
  }

  public boolean removeEmployeeReport(UUID reportId) {
    return reportIds.remove(reportId);
  }

  public Optional<LocalDateTime> getCreationDate() {
    return ofNullable(creationDate);
  }
}
