package com.attendance.core.attendance.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.attendance.core.attendance.data.request.AttendanceRequest;
import com.attendance.core.attendance.data.response.AttendanceResponse;
import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.entity.Subject;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import com.attendance.core.attendance.repository.AttendanceRepository;
import com.attendance.core.attendance.strategy.AttendanceStrategy;
import com.attendance.core.attendance.strategy.impl.AttendanceStrategyFactory;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.service.impl.EmployeeServiceImpl;
import com.attendance.utils.EmployeeTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {


    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private AttendanceStrategyFactory strategyFactory;

    @Mock
    private AttendanceStrategy attendanceStrategy;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;



    @Test
    void registerAttendance_shouldRegisterAttendanceSuccessfully_whenDateIsProvided() {
        LocalDate attendanceDate = LocalDate.of(2026, 6, 19);
        String notes = "Arrived on time";
        Employee employee = EmployeeTestDataFactory.employee();

        AttendanceRequest request = new AttendanceRequest(
                employee.getId(),
                AttendanceAction.SIGN_IN,
                attendanceDate,
                notes
        );

        final UUID employeeId= employee.getId();
        AttendanceRecord strategyRecord = AttendanceRecord.builder()
                .employee(Subject.builder()
                        .id(employeeId)
                        .name("John Doe")
                        .build())
                .date(attendanceDate)
                .action(AttendanceAction.SIGN_IN)
                .note(notes)
                .build();

        AttendanceRecord savedRecord = AttendanceRecord.builder()
                .employee(Subject.builder()
                        .id(employeeId)
                        .name("John Doe")
                        .build())
                .date(attendanceDate)
                .action(AttendanceAction.SIGN_IN)
                .note(notes)
                .build();

        UUID attendanceRecordId = UUID.randomUUID();
        savedRecord.setId(attendanceRecordId);

        when(employeeService.getEmployeeEntity(employeeId)).thenReturn(employee);
        when(strategyFactory.getStrategy(AttendanceAction.SIGN_IN)).thenReturn(attendanceStrategy);
        when(attendanceStrategy.execute(employee, attendanceDate, notes)).thenReturn(strategyRecord);
        when(attendanceRepository.save(strategyRecord)).thenReturn(savedRecord);

        AttendanceResponse response = attendanceService.registerAttendance(request);

        assertThat(response).isNotNull();
        assertThat(response.employeeId()).isEqualTo(employeeId);
        assertThat(response.employeeName()).isEqualTo("John Doe");
        assertThat(response.date()).isEqualTo(attendanceDate);
        assertThat(response.type()).isEqualTo(AttendanceType.PRESENT);

        verify(employeeService).getEmployeeEntity(employeeId);
        verify(strategyFactory).getStrategy(AttendanceAction.SIGN_IN);
        verify(attendanceStrategy).execute(employee, attendanceDate, notes);
        verify(attendanceRepository).save(strategyRecord);
    }

}