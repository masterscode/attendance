package com.attendance.core.attendance.strategy.impl;

import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import com.attendance.core.attendance.repository.AttendanceRepository;
import com.attendance.core.attendance.service.AttendanceFactory;
import com.attendance.core.attendance.strategy.AttendanceStrategy;
import com.attendance.core.employee.entity.Employee;
import com.attendance.internal.exception.AttendanceConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class SignInStrategy implements AttendanceStrategy {

    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceAction getSupportedAction() {
        return AttendanceAction.SIGN_IN;
    }

    @Override
    public AttendanceRecord execute(Employee employee, LocalDate date, String notes) {

        if (attendanceRepository.existsByEmployeeIdAndDate(employee.getId(), date)) {
            throw new AttendanceConflictException(
                    "Attendance record already exists for employee on " + date
            );
        }

        return AttendanceFactory.create(employee, AttendanceAction.SIGN_IN, notes);
    }
}
