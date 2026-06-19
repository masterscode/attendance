package com.attendance.core.employee.data.request;

import com.attendance.core.employee.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateEmployeeRequest(
        @NotEmpty(message = "First name is required")
        String firstName,
        @NotEmpty(message = "Last name is required")
        String lastName,
        @NotNull(message = "Gender is required")
        Gender gender,
        @NotEmpty(message = "Address is required")
        String address) {
}
