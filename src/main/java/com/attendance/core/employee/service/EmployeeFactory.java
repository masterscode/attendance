package com.attendance.core.employee.service;

import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.entity.Employee;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeFactory {

    public static Employee create(CreateEmployeeRequest request, Department department) {

        return Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .gender(request.gender())
                .address(request.address())
                .employeeType(request.employeeType())
                .department(department)
                .build();
    }
}
