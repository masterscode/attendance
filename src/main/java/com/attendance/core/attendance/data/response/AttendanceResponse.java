package com.attendance.core.attendance.data.response;


import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.entity.Subject;
import com.attendance.core.attendance.enums.AttendanceType;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record AttendanceResponse(
        UUID id,
        UUID employeeId,
        String employeeName,
        LocalDate date,
        AttendanceType type,
        String notes,
        Instant createdAt,
        Instant updatedAt
) {

    public static AttendanceResponse from(AttendanceRecord a) {
        Subject employee = a.getEmployee();

        return AttendanceResponse.builder()
                .id(employee.getId())
                .employeeId(employee.getId())
                .employeeName(employee.getName())
                .date(a.getDate())
                .type(a.computeAttendanceStatus())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();

    }
}
