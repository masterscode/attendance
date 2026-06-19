package com.attendance.core.attendance.api;

import com.attendance.commons.data.ApiResponse;
import com.attendance.core.attendance.data.request.AttendanceRequest;
import com.attendance.core.attendance.data.response.AttendanceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.UUID;

@RequestMapping("/api/v1")
@Tag(name = "Attendance", description = "Attendance register APIs")
public interface AttendanceApi {

    @PostMapping("/attendance")
    @Operation(summary = "Register attendance — SIGN_IN, SIGN_OUT, SICK_LEAVE, or ABSENT")
    ResponseEntity<ApiResponse<AttendanceResponse>> registerAttendance(
            @Valid @RequestBody AttendanceRequest request);

    @GetMapping("/attendance/employees/{employeeId}")
    @Operation(summary = "Get attendance register for an employee within a date range")
    ResponseEntity<ApiResponse<PagedModel<AttendanceResponse>>> getAttendance(
            @PathVariable UUID employeeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @PageableDefault Pageable pageable);

}
