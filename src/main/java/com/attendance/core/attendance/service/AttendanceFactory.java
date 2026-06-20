package com.attendance.core.attendance.service;

import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.entity.Subject;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import com.attendance.core.employee.entity.Employee;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceFactory {

    public  static AttendanceRecord create(Employee employee, AttendanceAction action, LocalDate date, String note) {

        return AttendanceRecord.builder()
                .employee(Subject.builder()
                        .id(employee.getId())
                        .name(String.format("%s %s", employee.getFirstName(), employee.getLastName()))
                        .build()
                )
                .action(action)
                .note(note)
                .date(date)
                .build();
    }
}
