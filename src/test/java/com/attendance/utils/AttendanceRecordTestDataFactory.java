package com.attendance.utils;

import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.entity.Subject;
import com.attendance.core.employee.entity.Employee;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@NoArgsConstructor
public class AttendanceRecordTestDataFactory {

    public static AttendanceRecord signIn(Employee employee) {
        return AttendanceRecord.builder()
                .employee(Subject.builder().id(employee.getId()).name(employee.getFirstName()).build())
                .date(LocalDate.now())
                .build();
    }

}
