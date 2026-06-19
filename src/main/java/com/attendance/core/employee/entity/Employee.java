package com.attendance.core.employee.entity;


import com.attendance.commons.entity.AbstractAuditingEntity;
import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee  extends AbstractAuditingEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type", nullable = false)
    private EmployeeType employeeType;

    @ManyToOne()
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}
