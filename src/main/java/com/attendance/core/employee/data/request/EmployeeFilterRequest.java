package com.attendance.core.employee.data.request;

import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import java.util.UUID;

public record EmployeeFilterRequest(
        String firstName,
        String lastName,
        UUID id,
        EmployeeType type,
        Gender gender,
        UUID departmentId,
        String departmentName
) {
}
