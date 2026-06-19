package com.attendance.core.department.data.response;


import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.enums.EmployeeType;
import lombok.Builder;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Builder
public record DepartmentResponse(
        UUID id,
        String name,
        EmployeeType category,
        Instant createdAt
) implements Serializable {

    public static DepartmentResponse from(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .createdAt(department.getCreatedAt())
                .build();
    }
}
