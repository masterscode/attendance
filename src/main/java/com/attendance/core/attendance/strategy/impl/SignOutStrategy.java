package com.attendance.core.attendance.strategy.impl;

import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import com.attendance.core.attendance.repository.AttendanceRepository;
import com.attendance.core.attendance.service.AttendanceFactory;
import com.attendance.core.attendance.strategy.AttendanceStrategy;
import com.attendance.core.employee.entity.Employee;
import com.attendance.internal.exception.AttendanceConflictException;
import com.attendance.internal.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class SignOutStrategy implements AttendanceStrategy {

    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceAction getSupportedAction() {
        return AttendanceAction.SIGN_OUT;
    }

    @Override
    public AttendanceRecord execute(Employee employee, LocalDate date, String notes) {

        final Boolean hasSignedIn = attendanceRepository
                .existsByDateAndActionAndEmployee_Id(date, AttendanceAction.SIGN_IN, employee.getId());

        if (BooleanUtils.isFalse(hasSignedIn)) {
            throw new ResourceNotFoundException(
                    "No sign-in record found for employee on " + date + ". Please sign in first."
            );
        }

        return AttendanceFactory.create(employee, AttendanceAction.SIGN_OUT, date, notes);
    }
}
