package com.attendance.core.employee.data.request;


import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateEmployeeRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotBlank(message = "Address is required")
        String address,

        @NotNull(message = "Employee type is required")
        EmployeeType employeeType,

        @NotNull(message = "Department ID is required")
        UUID departmentId
) {}
