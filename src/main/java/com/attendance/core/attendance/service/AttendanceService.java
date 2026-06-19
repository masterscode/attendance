package com.attendance.core.attendance.service;

import com.attendance.core.attendance.data.request.AttendanceRequest;
import com.attendance.core.attendance.data.response.AttendanceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.time.LocalDate;
import java.util.UUID;

public interface AttendanceService {

    AttendanceResponse registerAttendance(AttendanceRequest request);

    PagedModel<AttendanceResponse> getEmployeeAttendance(UUID employeeId, LocalDate from, LocalDate to, Pageable pageable);

}
