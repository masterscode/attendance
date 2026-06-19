package com.attendance.core.attendance.api.controller;


import com.attendance.commons.data.ApiResponse;
import com.attendance.core.attendance.api.AttendanceApi;
import com.attendance.core.attendance.data.request.AttendanceRequest;
import com.attendance.core.attendance.data.response.AttendanceResponse;
import com.attendance.core.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttendanceController implements AttendanceApi {

    private final AttendanceService attendanceService;

    @Override
    public ResponseEntity<ApiResponse<AttendanceResponse>> registerAttendance(
            AttendanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Attendance registered successfully",
                        attendanceService.registerAttendance(request)));
    }

    @Override
    public ResponseEntity<ApiResponse<PagedModel<AttendanceResponse>>>
                    getAttendance(UUID employeeId, LocalDate from, LocalDate to, Pageable pageable) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        attendanceService.getEmployeeAttendance(employeeId, from, to, pageable)
                )
        );
    }
}
