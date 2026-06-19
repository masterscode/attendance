package com.attendance.utils;

import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeTestDataFactory {

    public static Employee employee() {
        return Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .gender(Gender.MALE)
                .employeeType(EmployeeType.MEDICAL)
                .department(DepartmentTestDataFactory.department())
                .build();
    }

    public static Employee employee(Department department) {
        final Employee em = employee();
        em.setDepartment(department);

        return em;
    }
}
