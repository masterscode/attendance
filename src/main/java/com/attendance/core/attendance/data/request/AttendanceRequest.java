package com.attendance.core.attendance.data.request;

import com.attendance.core.attendance.enums.AttendanceAction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AttendanceRequest(

        @NotNull(message = "Employee ID is required")
        UUID employeeId,

        @NotNull(message = "Action is required (SIGN_IN, SIGN_OUT, SICK_LEAVE, ABSENT)")
        AttendanceAction action,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,

        String notes
) {}
