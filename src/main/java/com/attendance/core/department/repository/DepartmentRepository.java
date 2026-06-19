package com.attendance.core.department.repository;


import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
