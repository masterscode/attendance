package com.attendance.core.attendance.service.impl;


import com.attendance.core.attendance.data.request.AttendanceRequest;
import com.attendance.core.attendance.data.response.AttendanceResponse;
import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.repository.AttendanceRepository;
import com.attendance.core.attendance.repository.EmployeeAttendanceSpecification;
import com.attendance.core.attendance.service.AttendanceService;
import com.attendance.core.attendance.strategy.impl.AttendanceStrategyFactory;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeServiceImpl employeeService;
    private final AttendanceStrategyFactory strategyFactory;

    @Override
    @Transactional
    public AttendanceResponse registerAttendance(AttendanceRequest request) {
        Employee employee = employeeService.getEmployeeEntity(request.employeeId());
        LocalDate date = request.date() != null ? request.date() : LocalDate.now();

        AttendanceRecord record = strategyFactory
                .getStrategy(request.action())
                .execute(employee, date, request.notes());

        final AttendanceRecord savedRecord = attendanceRepository.save(record);

        return AttendanceResponse.from(savedRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<AttendanceResponse> getEmployeeAttendance(UUID employeeId, LocalDate from, LocalDate to, Pageable pageable) {

        final Page<AttendanceRecord> records =  attendanceRepository
                .findAll(EmployeeAttendanceSpecification.filter(employeeId, from, to), pageable);

        return new PagedModel<>(records.map(AttendanceResponse::from));
    }

}
