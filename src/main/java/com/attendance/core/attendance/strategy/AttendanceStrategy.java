package com.attendance.core.attendance.strategy;



import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.employee.entity.Employee;
import com.attendance.internal.exception.AttendanceConflictException;
import java.time.LocalDate;

/**
 * Strategy pattern — each concrete implementation handles one attendance action.
 * The factory selects the right strategy at runtime based on the requested action.
 */
public interface AttendanceStrategy {

    AttendanceAction getSupportedAction();

    AttendanceRecord execute(Employee employee, LocalDate date, String notes) throws AttendanceConflictException;
}
